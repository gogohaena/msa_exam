package com.sparta.msa_exam.order.global.exception;

import com.sparta.msa_exam.order.global.exception.CustomException;
import com.sparta.msa_exam.order.global.exception.ErrorCode;

public class BusinessException extends CustomException {
	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getStatus(), errorCode.getMessage());
	}

	public BusinessException(ErrorCode errorCode, String customMessage) {
		super(errorCode.getStatus(), customMessage);
	}
}
