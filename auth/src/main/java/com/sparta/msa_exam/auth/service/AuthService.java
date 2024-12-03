package com.sparta.msa_exam.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.msa_exam.auth.dto.UserSignUpRequest;
import com.sparta.msa_exam.auth.dto.UserSignUpResponse;
import com.sparta.msa_exam.auth.dto.UserSingInResponse;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.global.exception.BusinessException;
import com.sparta.msa_exam.auth.global.exception.ErrorCode;
import com.sparta.msa_exam.auth.global.util.JwtUtil;
import com.sparta.msa_exam.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	// 회원 등록
	@Transactional
	public UserSignUpResponse createUser(UserSignUpRequest request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS);
		}

		User user = User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		userRepository.save(user);

		return UserSignUpResponse.builder()
			.username(user.getUsername())
			.build();
	}

	// 로그인
	@Transactional(readOnly = true)
	public UserSingInResponse login(String username, String password) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BusinessException(ErrorCode.INVALID_PASSWORD);
		}

		String token = jwtUtil.createAccessToken(username);

		return new UserSingInResponse(token);
	}
}
