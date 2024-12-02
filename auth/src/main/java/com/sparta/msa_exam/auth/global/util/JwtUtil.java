package com.sparta.msa_exam.auth.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

	private final String issuer;
	private final Long accessExpiration;
	private final SecretKey secretKey;

	public JwtUtil(@Value("${spring.application.name}") String issuer,
		@Value("${service.jwt.secret-key}") String secretKey,
		@Value("${service.jwt.access-expiration}") Long accessExpiration) {
		this.issuer = issuer;
		this.accessExpiration = accessExpiration;
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
	}

	public String createAccessToken(String username, Map<String, Object> additionalClaims) {
		return Jwts.builder()
			.setClaims(additionalClaims)
			.claim("username", username)
			.setIssuer(issuer)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();
	}

	public Claims parseToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean isTokenValid(String token) {
		try {
			parseToken(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}