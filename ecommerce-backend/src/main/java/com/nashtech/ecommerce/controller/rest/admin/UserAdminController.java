package com.nashtech.ecommerce.controller.rest.admin;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.service.UserService;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseListUser findAllUser(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("status") Optional<Integer> statusOptional,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		int status=statusOptional.orElse(-1);
		return userService.findAllUser(name,status,currentPage, pageSize);
	}

	@GetMapping("/{id}")
	public ResponseUserDto findUserDtoById(@PathVariable Long id) {
		return userService.findUserDtoById(id);
	}
	@PostMapping
	public ResponseMessageDto signUp(@Valid @RequestBody RequestUserDto requestUserDto) {
		if (userService.existByEmail(requestUserDto.getEmail())) {
			return new ResponseMessageDto(HttpStatus.OK, "Email is existed",
					LocalDateTime.now());
		}
		if (userService.existByPhoneNumber(requestUserDto.getPhoneNumber())) {
			return new ResponseMessageDto(HttpStatus.OK, "Phone number is existed",
					LocalDateTime.now());
		}
		userService.createUser(requestUserDto);
		return new ResponseMessageDto(HttpStatus.OK, "Create user success!",
				LocalDateTime.now());

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
