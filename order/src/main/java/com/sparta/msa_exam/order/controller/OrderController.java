package com.sparta.msa_exam.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.order.dto.AddProductToOrderRequest;
import com.sparta.msa_exam.order.dto.AddProductToOrderResponse;
import com.sparta.msa_exam.order.dto.CreateOrderRequest;
import com.sparta.msa_exam.order.dto.CreateOrderResponse;
import com.sparta.msa_exam.order.dto.GetOrderResponse;
import com.sparta.msa_exam.order.global.common.dto.CommonResponse;
import com.sparta.msa_exam.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	private final OrderService orderService;

	// 주문 추가
	@PostMapping
	public ResponseEntity<CommonResponse<CreateOrderResponse>> createOrder(
		@RequestBody @Valid CreateOrderRequest request,
		@RequestParam(value = "fail", required = false) String fail) { // 상품 API 호출 실패 케이스

		log.info("Fail parameter: {}", fail);
		boolean isFail = fail != null;

		CreateOrderResponse response = orderService.createOrder(request, isFail);
		return ResponseEntity.ok(CommonResponse.success(response, "주문 등록에 성공하였습니다."));
	}

	// 주문에 상품 추가
	@PutMapping("/{orderId}")
	public ResponseEntity<CommonResponse<AddProductToOrderResponse>> addProductToOrder(
		@PathVariable Long orderId,
		@RequestBody @Valid AddProductToOrderRequest request
	) {
		AddProductToOrderResponse response = orderService.addProductToOrder(orderId, request.getProductId());
		return ResponseEntity.ok(CommonResponse.success(response, "상품 추가에 성공하였습니다."));
	}

	// 주문 단건 조회
	@GetMapping("/{orderId}")
	public ResponseEntity<CommonResponse<GetOrderResponse>> getOrderById(@PathVariable Long orderId) {
		GetOrderResponse response = orderService.getOrderById(orderId);
		return ResponseEntity.ok(CommonResponse.success(response));
	}
}
