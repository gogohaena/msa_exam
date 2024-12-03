package com.sparta.msa_exam.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

	@NotNull
	private String username;

	@NotNull
	private String password;

}
