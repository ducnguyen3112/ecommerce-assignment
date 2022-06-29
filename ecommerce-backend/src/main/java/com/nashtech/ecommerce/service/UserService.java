package com.nashtech.ecommerce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.SignUpDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.security.UserDetailsImpl;

public interface UserService {
	
	List<UserDto> findAllUserDtos();

	UserDto findUserDtoById(Long id);

	ResponseMessageDto deleteUser(Long id);

	UserDto findUserDtoByEmail(String email);

	UserDto updateUser(UserDto userDto);

	UserDto createUser(SignUpDto signUpDto);
	
	public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException;

	boolean existByEmail(String email);

	boolean existByPhoneNumber(String phoneNumber);
}