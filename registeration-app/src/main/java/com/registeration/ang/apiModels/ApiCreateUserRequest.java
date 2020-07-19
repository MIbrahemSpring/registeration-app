package com.registeration.ang.apiModels;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ApiCreateUserRequest {

	@NotEmpty(message = "Username is required")
	private String username;

	@NotEmpty(message = "Email is required")
	private String email;

	@NotEmpty(message = "Password is required")
	private String password;

	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;

}
