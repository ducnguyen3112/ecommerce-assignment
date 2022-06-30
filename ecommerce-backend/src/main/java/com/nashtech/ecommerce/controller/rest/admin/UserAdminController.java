package com.nashtech.ecommerce.controller.rest.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.service.UserService;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<ResponseUserDto> findAllUserDtos() {
		return userService.findAllUserDtos();
	}

	@GetMapping("/{id}")
	public ResponseUserDto findUserDtoById(@PathVariable Long id) {
		return userService.findUserDtoById(id);
	}

	@PostMapping
	public ResponseUserDto saveUserDto(@Valid @RequestBody RequestSignUpDto signUpDto) {
		return userService.createUser(signUpDto);
	}

	@PutMapping
	public ResponseUserDto updateUserDto(@Valid @RequestBody RequestUserDto userDto) {
		return userService.updateUser(userDto);
	}

	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

}
