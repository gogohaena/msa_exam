package com.sparta.msa_exam.order.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sparta.msa_exam.order.dto.ProductResponse;
import com.sparta.msa_exam.order.global.common.dto.CommonResponse;
import com.sparta.msa_exam.order.global.config.FeignClientConfig;

@FeignClient(
	name="product-service",
	configuration = FeignClientConfig.class
)
public interface ProductClient {

	// 상품 목록 조회 API
	@GetMapping("/products")
	CommonResponse<List<ProductResponse>> getProducts();

	// 상품 단건 조회 API
	@GetMapping("/products/{id}")
	CommonResponse<ProductResponse> getProductById(@PathVariable("id") Long id);

	// 존재하지 않는 API
	@GetMapping("/products/error")
	String errorProduct();

}
