package com.registeration.ang.errors;

public enum Errors {

	Headers_values_missing(1100), Invalid_json_request(1101), Invalid_API_Key(1102),
	Internal_server_error(5000),Create_User_Failed(2000);

	private final int Code;

	Errors(int Code) {
		this.Code = Code;
	}

	public int getCode() {
		return this.Code;
	}
}
