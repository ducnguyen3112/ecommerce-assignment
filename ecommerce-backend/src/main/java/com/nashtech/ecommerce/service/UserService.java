package com.nashtech.ecommerce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.security.UserDetailsImpl;

public interface UserService {
	
	List<ResponseUserDto> findAllUserDtos();

	ResponseUserDto findUserDtoById(Long id);

	ResponseMessageDto deleteUser(Long id);

	ResponseUserDto findUserDtoByEmail(String email);

	ResponseUserDto updateUser(RequestUserDto userDto);

	ResponseUserDto createUser(RequestSignUpDto signUpDto);
	
	public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException;

	boolean existByEmail(String email);

	boolean existByPhoneNumber(String phoneNumber);
}