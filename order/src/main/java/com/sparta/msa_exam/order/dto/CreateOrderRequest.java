package com.sparta.msa_exam.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {

	@NotNull
	private String username;

	@NotEmpty
	private List<Long> productIds;
}
