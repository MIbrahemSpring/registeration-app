package com.registeration.ang.apiModels;

import java.util.Date;

import lombok.Data;

@Data
public class ApiBaseModel {

	private Date CreatedAt;
	private Date modifiedAt;
	private String status;
}
