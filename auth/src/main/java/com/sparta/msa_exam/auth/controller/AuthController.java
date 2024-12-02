package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.UserSignUpRequest;
import com.sparta.msa_exam.auth.dto.UserSignUpResponse;
import com.sparta.msa_exam.auth.dto.UserSingInRequest;
import com.sparta.msa_exam.auth.dto.UserSingInResponse;
import com.sparta.msa_exam.auth.service.UserService;
import com.sparta.msa_exam.auth.global.common.dto.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;

	@PostMapping("/sign-up")
	public ResponseEntity<CommonResponse<UserSignUpResponse>> signUp(@RequestBody UserSignUpRequest request) {
		UserSignUpResponse response = userService.createUser(request);
		return ResponseEntity.ok(CommonResponse.success(response, "User registered successfully"));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<CommonResponse<UserSingInResponse>> signIn(@RequestBody UserSingInRequest request) {
		UserSingInResponse response = userService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(CommonResponse.success(response));
	}
}