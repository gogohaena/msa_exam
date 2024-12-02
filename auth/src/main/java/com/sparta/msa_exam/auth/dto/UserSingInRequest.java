package com.sparta.msa_exam.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserSingInRequest {

	@NotNull
	private String username;

	@NotNull
	private String password;
}
