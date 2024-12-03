package com.sparta.msa_exam.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AddProductToOrderResponse {
	private final Long orderId;
	private final List<Long> productIds;

	@Builder
	public AddProductToOrderResponse(Long orderId, List<Long> productIds) {
		this.orderId = orderId;
		this.productIds = productIds;
	}
}
