package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;

public interface UserService {
	
	List<UserDto> findAllUserDtos();

	UserDto findUserDtoById(Long id);

	ResponseMessageDto deleteUser(Long id);

	UserDto findUserDtoByEmail(String email);

	UserDto updateUser(UserDto userDto);

	UserDto createUser(User user);
}