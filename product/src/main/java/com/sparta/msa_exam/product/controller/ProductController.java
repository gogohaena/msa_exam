package com.sparta.msa_exam.product.controller;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.product.dto.ProductRequest;
import com.sparta.msa_exam.product.dto.ProductResponse;
import com.sparta.msa_exam.product.global.common.dto.CommonResponse;
import com.sparta.msa_exam.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @RefreshScope 애노테이션은 Spring 애플리케이션의 빈이 설정 변경을 반영할 수 있도록 하는 역할을 합니다.
 * 기본적으로 Spring 애플리케이션의 빈은 애플리케이션이 시작될 때 초기화되고, 설정 값이 변경되더라도 해당 빈은 갱신되지 않습니다.
 * 이 애노테이션을 사용하면 /actuator/refresh 엔드포인트를 호출하여 설정 변경 사항을 동적으로 반영할 수 있습니다.
 */
@RefreshScope
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	// 상품 등록
	@PostMapping
	public ResponseEntity<CommonResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest request) {
		ProductResponse response = productService.createProduct(request);
		return ResponseEntity.ok(CommonResponse.success(response, "상품 등록에 성공하였습니다"));
	}

	// 상품 목록 조회
	@GetMapping
	public ResponseEntity<CommonResponse<List<ProductResponse>>> getProducts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Pageable pageable = PageRequest.of(page, size);
		List<ProductResponse> response = productService.getProducts(pageable);
		return ResponseEntity.ok(CommonResponse.success(response));
	}

	// 상품 상세 조회
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable Long id) {
		ProductResponse response = productService.getProductById(id);
		return ResponseEntity.ok(CommonResponse.success(response));
	}
}