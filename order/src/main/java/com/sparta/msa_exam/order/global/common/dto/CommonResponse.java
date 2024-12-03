package com.sparta.msa_exam.order.global.common.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

	private final int statusCode;  // HTTP 상태 코드
	private final String message; // 응답 메시지
	private final T data;         // 응답 데이터

	@Builder
	private CommonResponse(int statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	// 성공 응답 생성
	public static <T> CommonResponse<T> success(T data) {
		return CommonResponse.<T>builder()
			.statusCode(HttpStatus.OK.value())
			.message("Success")
			.data(data)
			.build();
	}

	public static <T> CommonResponse<T> success(T data, String customMessage) {
		return CommonResponse.<T>builder()
			.statusCode(HttpStatus.OK.value())
			.message(customMessage)
			.data(data)
			.build();
	}

	// 실패 응답 생성
	public static <T> CommonResponse<T> fail(HttpStatus status, String customMessage) {
		return CommonResponse.<T>builder()
			.statusCode(status.value())
			.message(customMessage)
			.data(null)
			.build();
	}

	// 실패 응답 (디폴트 메시지 사용)
	public static <T> CommonResponse<T> fail(HttpStatus status) {
		return CommonResponse.<T>builder()
			.statusCode(status.value())
			.message(status.getReasonPhrase())
			.data(null)
			.build();
	}
}
