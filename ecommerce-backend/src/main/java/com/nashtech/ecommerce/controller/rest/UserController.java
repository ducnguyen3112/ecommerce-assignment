package com.nashtech.ecommerce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDto> findAllUserDtos() {
		return userService.findAllUserDtos();
	}

	@GetMapping("/{id}")
	public UserDto findUserDtoById(@PathVariable Long id) {
		return userService.findUserDtoById(id);
	}

	@PostMapping
	public UserDto saveUserDto(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping
	public UserDto updateUserDto(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
