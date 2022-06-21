package com.nashtech.ecommerce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl UserService;
	
	
	@GetMapping
	public List<UserDto> findAllUserDtos() {
		return UserService.findAllUserDtos();
	}
	
}
