package com.registeration.ang.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.registeration.ang.dto.UserDto;
import com.registeration.ang.entities.UserEntity;
import com.registeration.ang.exceptions.EmailAlreadyExistsException;
import com.registeration.ang.repositories.UserRepository;
import com.registeration.ang.utils.ModelMapping;
import com.registeration.ang.utils.PasswordEncoder;
import com.registeration.ang.utils.Utility;
import com.registeration.ang.utils.Enums.Status;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private Utility utility;

	@Autowired
	private PasswordEncoder bCryptPassword;

	@Autowired
	private ModelMapping modelMapping;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException("Credentials are not correct");

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String name) {
		UserEntity userEntity = userRepo.findByEmail(name);
		UserDto returnValue = modelMapping.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDto createUser(UserDto userDto) {

		if (userRepo.findByEmail(userDto.getEmail()) != null)
			throw new EmailAlreadyExistsException("Email already exists");

		UserEntity userEntity = new UserEntity.UserEntityBuilder().setUserId(utility.generateUserId(22, "user_"))
				.setUsername(userDto.getUsername()).setEmail(userDto.getEmail())
				.setEncryptedPassword(bCryptPassword.encode(userDto.getPassword())).build();
		
		userEntity.setCreatedAt(new Date());
		userEntity.setStatus(Status.ACTIVE.toString());
		UserEntity user = userRepo.save(userEntity);
		UserDto returnValue = modelMapping.map(user, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String id) {
		UserEntity userEntity = userRepo.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException("");
		UserDto userDto = modelMapping.map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public List<UserDto> getUsers() {
		List<UserDto> users = new ArrayList<UserDto>();
		List<UserEntity> allUsers = userRepo.findAll();
		modelMapping.map(allUsers, users);
		return users;
	}

}
