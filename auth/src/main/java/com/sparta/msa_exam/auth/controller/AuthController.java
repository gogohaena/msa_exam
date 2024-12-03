package com.sparta.msa_exam.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.auth.dto.UserSignUpRequest;
import com.sparta.msa_exam.auth.dto.UserSignUpResponse;
import com.sparta.msa_exam.auth.dto.UserSingInRequest;
import com.sparta.msa_exam.auth.dto.UserSingInResponse;
import com.sparta.msa_exam.auth.global.common.dto.CommonResponse;
import com.sparta.msa_exam.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	// 회원 가입
	@PostMapping("/sign-up")
	public ResponseEntity<CommonResponse<UserSignUpResponse>> signUp(@RequestBody UserSignUpRequest request) {
		UserSignUpResponse response = authService.createUser(request);
		return ResponseEntity.ok(CommonResponse.success(response, "회원 가입에 성공하였습니다."));
	}

	// 로그인
	@PostMapping("/sign-in")
	public ResponseEntity<CommonResponse<UserSingInResponse>> signIn(@RequestBody UserSingInRequest request) {
		UserSingInResponse response = authService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(CommonResponse.success(response));
	}
}