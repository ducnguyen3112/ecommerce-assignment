package com.nashtech.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;

public interface UserService{
	public List<UserDto> findAllUserDtos();
	public UserDto findUserDtoById(Long id);
	public UserDto saveUser(UserDto userDto);
	UserDto updateUser(Map<Object, Object> fields, Long id);
	ResponseMessageDto delelteUser(Long id);
}