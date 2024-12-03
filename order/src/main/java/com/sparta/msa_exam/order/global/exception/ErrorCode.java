package com.sparta.msa_exam.order.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order not found"),
	INVALID_ORDER_ID(HttpStatus.BAD_REQUEST, "Invalid Order ID"),
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found"),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token expired"),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}