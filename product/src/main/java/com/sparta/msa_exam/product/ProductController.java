package com.sparta.msa_exam.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/{id}")
	public String getProduct(@PathVariable("id") String id) {
		return "Product Id: " + id + ", Server Port: " + serverPort;
	}
}

