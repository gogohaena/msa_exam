package com.sparta.msa_exam.product.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found"),
	INVALID_PRODUCT_ID(HttpStatus.BAD_REQUEST, "Invalid product ID"),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token expired"),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}