package com.nashtech.ecommerce.controller.rest.user;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestSignInDto;
import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseSignIn;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.security.jwt.JwtUtils;
import com.nashtech.ecommerce.service.RoleService;
import com.nashtech.ecommerce.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody RequestSignInDto signInDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInDto.getEmail(),
						signInDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		return ResponseEntity.ok(new ResponseSignIn(userDetailsImpl.getName(),
				userDetailsImpl.getUsername(), userDetailsImpl.getAuthorities(), token));

	}

	@PostMapping("/signup")
	public ResponseMessageDto signUp(@Valid @RequestBody RequestSignUpDto signUpDto) {
		if (userService.existByEmail(signUpDto.getEmail())) {
			return new ResponseMessageDto(HttpStatus.OK, "Email is existed",
					LocalDateTime.now());
		}
		if (userService.existByPhoneNumber(signUpDto.getPhoneNumber())) {
			return new ResponseMessageDto(HttpStatus.OK, "Phone number is existed",
					LocalDateTime.now());
		}
		userService.createUser(signUpDto);
		return new ResponseMessageDto(HttpStatus.OK, "Register success",
				LocalDateTime.now());

	}
}
