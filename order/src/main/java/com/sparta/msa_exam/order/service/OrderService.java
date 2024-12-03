package com.sparta.msa_exam.order.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sparta.msa_exam.order.dto.AddProductToOrderResponse;
import com.sparta.msa_exam.order.dto.CreateOrderRequest;
import com.sparta.msa_exam.order.dto.CreateOrderResponse;
import com.sparta.msa_exam.order.dto.GetOrderResponse;
import com.sparta.msa_exam.order.dto.ProductResponse;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.entity.OrderStatus;
import com.sparta.msa_exam.order.feignclient.ProductClient;
import com.sparta.msa_exam.order.global.common.dto.CommonResponse;
import com.sparta.msa_exam.order.global.exception.BusinessException;
import com.sparta.msa_exam.order.global.exception.CustomException;
import com.sparta.msa_exam.order.global.exception.ErrorCode;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;
	private final ProductClient productClient;

	// 주문 등록
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackCreateOrder")
	@Transactional
	public CreateOrderResponse createOrder(CreateOrderRequest request, boolean isFail) {
		log.info("isFail : " + isFail);

		// 상품 API 호출 실패 케이스
		if (isFail) {
			log.info("productClient.errorProduct 호출");
			productClient.errorProduct();
		}

		// 상품 검증
		validateProducts(request.getProductIds());

		// 주문 생성
		Order order = Order.builder()
			.username(request.getUsername())
			.status(OrderStatus.PENDING) // 기본 상태
			.build();

		// 주문 상품 생성
		List<OrderProduct> products = request.getProductIds().stream()
			.map(productId -> OrderProduct.builder().productId(productId).build())
			.toList();

		products.forEach(order::addProduct);

		Order savedOrder = orderRepository.save(order);
		return new CreateOrderResponse(savedOrder);
	}

	// 주문에 상품 추가
	@Transactional
	public AddProductToOrderResponse addProductToOrder(Long orderId, Long productId) {
		// 상품 검증
		validateProducts(Collections.singletonList(productId));

		// 주문 조회
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

		// 상품 추가
		OrderProduct orderProduct = OrderProduct.builder()
			.order(order)
			.productId(productId)
			.build();
		order.addProduct(orderProduct);

		orderRepository.save(order); // 변경 사항 저장

		return AddProductToOrderResponse.builder()
			.orderId(order.getId())
			.productIds(order.getProducts().stream()
				.map(OrderProduct::getProductId)
				.collect(Collectors.toList()))
			.build();
	}

	// 주문 상세 조회
	public GetOrderResponse getOrderById(Long orderId) {
		// 주문 조회
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

		// 주문 상품 조회
		List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);

		// 상품 상세 정보 생성
		List<GetOrderResponse.ProductDetail> productDetails = orderProducts.stream()
			.map(orderProduct -> {
				ProductResponse productResponse = getProductById(orderProduct.getProductId());
				return new GetOrderResponse.ProductDetail(
					productResponse.getProductId(),
					productResponse.getName(),
					productResponse.getSupplyPrice(),
					productResponse.getDescription()
				);
			})
			.collect(Collectors.toList());

		// GetOrderResponse 생성 및 반환
		return new GetOrderResponse(
			order.getId(),
			order.getUsername(),
			order.getStatus().name(),
			productDetails
		);
	}

	// 상품 검증
	private void validateProducts(List<Long> productIds) {
		// 상품 목록 조회
		List<ProductResponse> availableProducts = getProducts();

		// 상품 ID를 Set으로 변환 (검색 속도 최적화)
		Set<Long> availableProductIds = availableProducts.stream()
			.map(ProductResponse::getProductId)
			.collect(Collectors.toSet());

		// 검증: 주어진 productIds가 존재하는지 확인
		productIds.forEach(productId -> {
			if (!availableProductIds.contains(productId)) {
				throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND,
					String.format("Product with ID %d not found", productId));
			}
		});
	}

	// 상품 목록 조회 API
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProducts")
	private List<ProductResponse> getProducts() {
		// FeignClient 이용
		CommonResponse<List<ProductResponse>> response = productClient.getProducts();

		if (response == null || response.getData() == null || response.getStatusCode() != HttpStatus.OK.value()) {
			throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
		}

		return response.getData();
	}

	// 상품 단건 조회 API
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProductById")
	private ProductResponse getProductById(Long productId) {
		// 상품 목록 조회 API 호출
		CommonResponse<ProductResponse> response = productClient.getProductById(productId);

		if (response == null || response.getData() == null || response.getStatusCode() != HttpStatus.OK.value()) {
			throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
		}

		return response.getData();
	}

	// 주문 등록 Fallback
	public CreateOrderResponse fallbackCreateOrder(CreateOrderRequest request, boolean fail, Throwable ex) {
		log.error("Fallback triggered for createOrder. Reason: {}", ex.getMessage(), ex);
		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "잠시 후에 주문 추가를 요청 해주세요.");
	}

	// 상품 목록 조회 API Fallback
	private List<ProductResponse> fallbackGetProducts(Throwable ex) {
		log.error("Failed to fetch products from product service. Reason: {}", ex.getMessage(), ex);
		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "상품 목록 조회에 실패했습니다. 잠시 후 다시 시도해주세요.");
	}

	// 상품 단건 조회 API Fallback
	private ProductResponse fallbackGetProductById(Long productId, Throwable ex) {
		log.error("Failed to fetch product with ID {} from product service. Reason: {}", productId, ex.getMessage(), ex);
		throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "상품 조회에 실패했습니다. 잠시 후 다시 시도해주세요.");
	}

}
