package com.sparta.msa_exam.product.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final HttpStatus status;
	private final String message;

	public CustomException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
}
