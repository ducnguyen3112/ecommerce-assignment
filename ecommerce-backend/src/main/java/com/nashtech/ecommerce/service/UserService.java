package com.nashtech.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;

public interface UserService{
	public List<UserDto> findAllUserDtos();
	public UserDto findUserDtoById(Long id);
	UserDto updateUser(Map<Object, Object> fields, Long id);
	ResponseMessageDto deleteUser(Long id);
	UserDto findUserDtoByEmail(String email);
	UserDto saveUser(User user);
}