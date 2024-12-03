package com.sparta.msa_exam.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetOrderResponse {
	private final Long orderId;
	private final String username;
	private final String status;
	private final List<ProductDetail> products;

	@Getter
	@AllArgsConstructor
	public static class ProductDetail {
		private final Long productId;
		private final String name;
		private final int supplyPrice;
		private final String description;
	}
}
