package com.sparta.msa_exam.auth.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid password"),
	USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "Username already exists"),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token expired"),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}