package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    ResponseUserDto findUserById(Long id);

    ResponseEntity deleteUser(Long id);

    ResponseUserDto findUserDtoByEmail(String email);

    ResponseUserDto updateUser(RequestUserDto userDto);

    public UserDetailsImpl loadUserByUsername(String email)
            throws UsernameNotFoundException;

    boolean existByEmail(String email);

    boolean existByPhoneNumber(String phoneNumber);

    ResponseListUser findAllUser(String name, int status, int page, int size);

    ResponseUserDto createUser(RequestUserDto requestUserDto);

    ResponseUserDto signUp(RequestSignUpDto signUpDto);
}