package com.nashtech.ecommerce.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService UserService;
	
	
	@GetMapping
	public List<UserDto> findAllUserDtos() {
		return UserService.findAllUserDtos();
	}
	
}
