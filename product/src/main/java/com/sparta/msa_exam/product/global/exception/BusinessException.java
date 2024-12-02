package com.sparta.msa_exam.product.global.exception;

import com.sparta.msa_exam.product.global.exception.CustomException;
import com.sparta.msa_exam.product.global.exception.ErrorCode;

public class BusinessException extends CustomException {
	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getStatus(), errorCode.getMessage());
	}
}
