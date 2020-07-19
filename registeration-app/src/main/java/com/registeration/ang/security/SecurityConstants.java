package com.registeration.ang.security;

import com.registeration.ang.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 86400000;    // ONE DAT VALIDITY
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/v1/users/signup";
	public static final String SIGN_IN_URL = "/api/v1/users/signin";
	public static final String SIGN_OUT_URL = "/signout";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getSecretToken();
	}
}
