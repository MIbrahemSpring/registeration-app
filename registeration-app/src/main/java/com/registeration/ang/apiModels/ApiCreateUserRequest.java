package com.registeration.ang.apiModels;

import javax.validation.constraints.NotEmpty;

import com.registeration.ang.annotations.FieldMatch;

import lombok.Data;

@Data
@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "confirm password must match the password") })
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
