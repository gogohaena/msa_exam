package com.sparta.msa_exam.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/{orderId}")
	public String getOrder(@PathVariable("orderId") String orderId) {
		return orderService.getOrder(orderId);
	}

	@GetMapping
	public String getOrder() {
	 	return "Order details";
	}
}
