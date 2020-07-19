package com.registeration.ang.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registeration.ang.dto.UserDto;

public interface IUserService extends UserDetailsService {
	UserDto createUser(UserDto userDto);
	UserDto getUser(String name);
	UserDto getUserByUserId(String id);
	List<UserDto> getUsers();
}
