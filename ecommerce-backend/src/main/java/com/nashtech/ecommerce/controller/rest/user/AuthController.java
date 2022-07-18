package com.nashtech.ecommerce.controller.rest.user;

import com.nashtech.ecommerce.dto.request.RequestSignInDto;
import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseSignIn;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.security.jwt.JwtUtils;
import com.nashtech.ecommerce.service.RoleService;
import com.nashtech.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final UserService userService;
    final RoleService roleService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final JwtUtils jwtUtils;
    final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signin")
    @Operation(summary = "Log in to the website",
            tags = {"User"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signIn(@Valid @RequestBody RequestSignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(),
                        signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new ResponseSignIn(userDetailsImpl.getId(), userDetailsImpl.getName(),
                userDetailsImpl.getUsername(), userDetailsImpl.getImage(), userDetailsImpl.getAuthorities(), token));

    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "User registration",
            tags = {"User"})
    public ResponseMessageDto signUp(@Valid @RequestBody RequestSignUpDto signUpDto) {
        if (userService.isEmailExist(signUpDto.getEmail())) {
            return new ResponseMessageDto(HttpStatus.OK, "Email is existed",
                    LocalDateTime.now());
        }
        if (userService.isPhoneNumberExist(signUpDto.getPhoneNumber())) {
            return new ResponseMessageDto(HttpStatus.OK, "Phone number is existed",
                    LocalDateTime.now());
        }
        userService.signUp(signUpDto);
        return new ResponseMessageDto(HttpStatus.OK, "Register success",
                LocalDateTime.now());

    }
}
