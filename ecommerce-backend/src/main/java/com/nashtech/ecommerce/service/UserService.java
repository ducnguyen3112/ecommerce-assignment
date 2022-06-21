package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.UserDto;

public interface UserService{
	public List<UserDto> findAllUserDtos();
}