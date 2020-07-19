package com.registeration.ang.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.registeration.ang.apiModels.ApiCreateUserRequest;
import com.registeration.ang.dto.UserDto;
import com.registeration.ang.errors.Errors;
import com.registeration.ang.errors.IErrorServices;
import com.registeration.ang.services.IUserService;
import com.registeration.ang.uiModels.UserResponseModel;
import com.registeration.ang.utils.ModelMapping;
import com.registeration.ang.utils.ObjMapper;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ModelMapping modelMapping;

	@Autowired
	private ObjMapper objMapper;

	@Autowired
	private IErrorServices _errorServices;

	@Autowired
	private IUserService userService;

	// create a new user
	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> createUser(@Valid @RequestBody ApiCreateUserRequest apiCreateUserRequest) {
		logger.info("-------------------- Start creating a new user --------------------");
		// fill UserDto from the requestBody
		UserDto userDto = modelMapping.map(apiCreateUserRequest, UserDto.class);

		// create a new user
		UserDto savedUser = userService.createUser(userDto);

		// fill the response
		UserResponseModel response = modelMapping.map(savedUser, UserResponseModel.class);

		try {
			return new ResponseEntity<Object>(objMapper.writeValueAsString(response), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error("-------------------- Error while creating a new user --------------------");
			return _errorServices.Error(Errors.Create_User_Failed.getCode(), Errors.Create_User_Failed.toString(),
					"Failed to create a new user", HttpStatus.BAD_REQUEST);
		}

	}

	// get a single user by userId
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUser(@Valid @PathVariable String userId) {

		UserDto userDto = userService.getUserByUserId(userId);

		UserResponseModel response = modelMapping.map(userDto, UserResponseModel.class);

		try {
			return new ResponseEntity<>(objMapper.writeValueAsString(response), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error("-------------------- Error while creating a new ledger --------------------");
			logger.error(e.getMessage());
			return _errorServices.Error(Errors.Create_User_Failed.getCode(), Errors.Create_User_Failed.toString(),
					"Failed to create a new Ledger", HttpStatus.BAD_REQUEST);
		}

	}

	// get list of friends
	@PostMapping("/list")
	public ResponseEntity<Object> getUsers() {

		List<UserDto> userDto = userService.getUsers();

//			UserResponseModel response = modalMapping.map(userDto, UserResponseModel.class);

		try {
			return new ResponseEntity<>(objMapper.writeValueAsString(userDto), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error("-------------------- Error while creating a new ledger --------------------");
			logger.error(e.getMessage());
			return _errorServices.Error(Errors.Create_User_Failed.getCode(), Errors.Create_User_Failed.toString(),
					"Failed to create a new Ledger", HttpStatus.BAD_REQUEST);
		}

	}
}
