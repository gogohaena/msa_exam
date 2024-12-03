package com.sparta.msa_exam.order.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.entity.OrderStatus;

import lombok.Getter;

@Getter
public class CreateOrderResponse {
	private final Long orderId;
	private final String username;
	private final OrderStatus status;
	private final List<Long> productIds;

	public CreateOrderResponse(Order order) {
		this.orderId = order.getId();
		this.username = order.getUsername();
		this.status = order.getStatus();
		this.productIds = order.getProducts().stream()
			.map(OrderProduct::getProductId)
			.collect(Collectors.toList());
	}
}
