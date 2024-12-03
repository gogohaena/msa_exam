package com.sparta.msa_exam.order.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Retryer;

@Configuration
public class FeignClientConfig {
	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL; // 요청/응답의 모든 세부 정보 로깅
	}

	@Bean
	public Retryer feignRetryer() {
		return new Retryer.Default(100, 1000, 3); // 3번 재시도, 간격은 100~1000ms
	}

	// @Bean
	// public RequestInterceptor requestInterceptor() {
	// 	return requestTemplate -> {
	// 		requestTemplate.header("Authorization", "Bearer my-token");
	// 		requestTemplate.header("Custom-Header", "CustomValue");
	// 	};
	// }
}
