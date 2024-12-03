package com.sparta.msa_exam.order.entity;

public enum OrderStatus {
	PENDING, // 주문 대기
	ACCEPTED, // 주문 접수
	DELIVERING, // 배송 중
	COMPLETE, // 완료
	CANCELED // 취소
}
