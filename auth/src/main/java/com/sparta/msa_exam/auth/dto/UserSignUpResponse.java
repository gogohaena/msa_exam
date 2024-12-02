package com.sparta.msa_exam.auth.dto;

import com.fasterxml.jackson.core.JsonToken;
import com.sparta.msa_exam.auth.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UserSignUpResponse {

	private String username;

}
