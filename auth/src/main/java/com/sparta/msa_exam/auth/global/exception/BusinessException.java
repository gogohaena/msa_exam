package com.sparta.msa_exam.auth.global.exception;

public class BusinessException extends CustomException {
	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getStatus(), errorCode.getMessage());
	}
}
