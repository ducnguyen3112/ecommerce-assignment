package com.nashtech.ecommerce.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping
	public List<UserDto> findAllUserDtos() {
		return userService.findAllUserDtos();
	}
	@GetMapping("/{id}")
	public UserDto findUserDtoById(@PathVariable Long id) {
		return userService.findUserDtoById(id);
	}
	@PostMapping
	public UserDto saveUserDto(@RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}
	@PatchMapping("/{id}")
	public UserDto updateUserDto(@RequestBody Map<Object, Object> fields, @PathVariable Long id) {
		return userService.updateUser(fields, id);
	}
	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteUser(@PathVariable Long id) {
		return userService.delelteUser(id);
	}
}
