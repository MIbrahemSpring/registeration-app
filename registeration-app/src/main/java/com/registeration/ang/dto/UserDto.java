package com.registeration.ang.dto;

import java.io.Serializable;

import com.registeration.ang.annotations.FieldMatch;
import com.registeration.ang.apiModels.ApiBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match") })
public class UserDto extends ApiBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;

}
