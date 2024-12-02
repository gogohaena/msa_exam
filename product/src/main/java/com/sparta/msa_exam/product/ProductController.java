package com.sparta.msa_exam.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") String id) {
		return productService.getProductDetails(id);
	}

	// @Value("${server.port}")
	// private String serverPort;
	//
	// @GetMapping("/{id}")
	// public String getProduct(@PathVariable("id") String id) {
	// 	return "Product Id: " + id + ", Server Port: " + serverPort;
	// }
}

