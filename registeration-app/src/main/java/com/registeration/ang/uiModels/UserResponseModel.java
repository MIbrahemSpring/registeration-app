package com.registeration.ang.uiModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.registeration.ang.apiModels.ApiBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(value = Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponseModel extends ApiBaseModel {

	private String userId;
	private String username;
	private String email;
	private String status;

}
