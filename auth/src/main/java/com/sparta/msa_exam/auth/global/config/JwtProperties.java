package com.sparta.msa_exam.auth.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "service.jwt")
public class JwtProperties {
	private String secretKey;
	private Long accessExpiration;
}