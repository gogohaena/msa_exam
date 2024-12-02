package com.sparta.msa_exam.auth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

	@Id
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Builder
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
