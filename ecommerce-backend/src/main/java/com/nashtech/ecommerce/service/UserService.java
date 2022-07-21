package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.service.impl.RequestEditUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    ResponseUserDto getUser(Long id);


    ResponseUserDto updateUser(RequestEditUser requestEditUser, Long id);

    ResponseUserDto deleteUser(Long id);

    ResponseUserDto getUser(String email);


    public UserDetailsImpl loadUserByUsername(String email)
            throws UsernameNotFoundException;

    boolean isEmailExist(String email);

    boolean isPhoneNumberExist(String phoneNumber);

    ResponseListUser findAllUser(String name, int status, int page, int size);

    ResponseUserDto createUser(RequestUserDto requestUserDto);

    ResponseUserDto signUp(RequestSignUpDto signUpDto);
}