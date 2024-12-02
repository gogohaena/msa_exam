package com.sparta.msa_exam.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProductDetails")
	public Product getProductDetails(String productId) {
		if ("111".equals(productId)) {
			throw new RuntimeException("Empty response body");
		}
		return new Product(
			productId,
			"Sample Product : " + productId
		);
	}

	public Product fallbackGetProductDetails(String productId, Throwable t) {
		return new Product(
			productId,
			"Fallback Product : " + productId
		);
	}
}