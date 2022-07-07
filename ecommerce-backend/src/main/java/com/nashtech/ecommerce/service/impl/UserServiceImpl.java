package com.nashtech.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.TypeMismatchException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.enums.RoleName;
import com.nashtech.ecommerce.enums.UserStatus;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public UserDetailsImpl loadUserByUsername(String email)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Did not find user has email = " + email));
		return UserDetailsImpl.build(user);
	}

	@Override
	public ResponseListUser findAllUser(String name, int status, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<User> userPage = null;
		if (status == -1) {
			if (StringUtils.hasText(name)) {
				userPage = userRepository.findByFullNameContaining(name, pageable);
			} else {
				userPage = userRepository.findAll(pageable);
			}
		} else if (status == 1 || status == 0) {
			if (StringUtils.hasText(name)) {
				userPage = userRepository.findByStatusAndFullNameContaining(name, status,
						pageable);
			} else {
				userPage = userRepository.findByStatus(status, pageable);
			}

		}
		return ResponseListUser.builder().totalUser(userPage.getTotalElements())
				.perPage(userPage.getNumberOfElements())
				.currentPage(userPage.getNumber() + 1).lastPage(userPage.getTotalPages())
				.responseUserDtos(modelMapper.map(userPage.getContent(),
						new TypeToken<List<ResponseUserDto>>() {
						}.getType()))
				.build();
	}

	@Override
	public ResponseUserDto findUserDtoById(Long id) {

		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user has id = " + id));
		return modelMapper.map(user, ResponseUserDto.class);
	}

	@Override
	public ResponseUserDto createUser(RequestUserDto signUpDto) {
		signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		User user = modelMapper.map(signUpDto, User.class);
		user.setRegisteredAt(new Date());
		user.setStatus(UserStatus.ACTIVE);
		Set<String> strRoles = signUpDto.getRoles();
		Set<Role> roles = new HashSet<Role>();
		strRoles.forEach(strRole -> {
			if (strRole.equalsIgnoreCase("admin")) {
				roles.add(new Role((long) RoleName.ROLE_ADMIN.getValue(),
						RoleName.ROLE_ADMIN));
			} else if (strRole.equalsIgnoreCase("user")) {
				roles.add(new Role((long) RoleName.ROLE_ADMIN.getValue(),
						RoleName.ROLE_USER));
			} else {
					throw new TypeMismatchException("Roles must be admin or user ");
			}
		});
		user.setRoles(roles);
		return modelMapper.map(userRepository.save(user), ResponseUserDto.class);
	}

	@Override
	public ResponseUserDto signUp(RequestSignUpDto signUpDto) {
		signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		User user = modelMapper.map(signUpDto, User.class);
		user.setRegisteredAt(new Date());
		user.setStatus(UserStatus.ACTIVE);

		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role((long) RoleName.ROLE_USER.getValue(), RoleName.ROLE_USER));
		user.setRoles(roles);
		return modelMapper.map(userRepository.save(user), ResponseUserDto.class);
	}

	@Override
	public ResponseUserDto updateUser(RequestUserDto userDto) {
		User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Did not find user has email = " + userDto.getId()));
		modelMapper.map(userDto, user);
		user = userRepository.save(user);
		return modelMapper.map(user, ResponseUserDto.class);
	}

	@Override
	public ResponseMessageDto deleteUser(Long id) {
		userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user with id = " + id));

		return new ResponseMessageDto(HttpStatus.ACCEPTED, "Deleted user with id= " + id,
				LocalDateTime.now());
	}

	@Override
	public ResponseUserDto findUserDtoByEmail(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Did not find user has email = " + email));
		return modelMapper.map(user, ResponseUserDto.class);
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existByPhoneNumber(String phoneNumber) {
		return userRepository.existsByPhoneNumber(phoneNumber);
	}
}
