package com.registeration.ang.dto;

import java.io.Serializable;

import com.registeration.ang.apiModels.ApiBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends ApiBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;

}
