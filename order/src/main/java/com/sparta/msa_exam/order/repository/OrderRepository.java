package com.sparta.msa_exam.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.global.exception.BusinessException;
import com.sparta.msa_exam.order.global.exception.ErrorCode;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

