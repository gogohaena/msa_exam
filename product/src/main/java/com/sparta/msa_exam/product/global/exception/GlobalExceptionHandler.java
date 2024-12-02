package com.sparta.msa_exam.product.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.msa_exam.product.global.common.dto.CommonResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 비즈니스 예외 처리
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException ex) {
		return ResponseEntity
			.status(ex.getStatus())
			.body(CommonResponse.fail(ex.getStatus(), ex.getMessage()));
	}

	// 기타 예외 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse<Void>> handleException(Exception ex) {
		ex.printStackTrace(); // 로그용
		return ResponseEntity
			.status(500)
			.body(CommonResponse.fail(HttpStatus.valueOf(500), "An unexpected error occurred"));
	}
}
