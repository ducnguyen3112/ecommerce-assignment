package com.nashtech.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.SignUpDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.entity.RoleName;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.service.RoleService;
import com.nashtech.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	private RoleService roleService;
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
			PasswordEncoder passwordEncoder,RoleService roleService) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}

	@Override
	public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user has email = " + email));
		return UserDetailsImpl.build(user);
	}

	@Override
	public List<UserDto> findAllUserDtos() {
		List<User> userList = userRepository.findAll();
		return modelMapper.map(userList, new TypeToken<List<UserDto>>() {
		}.getType());
	}

	@Override
	public UserDto findUserDtoById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new ResourceNotFoundException("Did not find user with id = " + id);
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto createUser(SignUpDto signUpDto) {
		signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		User user=modelMapper.map(signUpDto, User.class);
		user.setRegisteredAt(new Date());
		user.setStatus(1);
		Set<String> roleStr=signUpDto.getRoles();
		Set<Role> roles=new HashSet<Role>();
		roleStr.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole=roleService.findByName(RoleName.ROLE_ADMIN);
				roles.add(adminRole);
				break;
			default:
				Role userRole=roleService.findByName(RoleName.ROLE_USER);
				roles.add(userRole);
			}
		});
		user.setRoles(roles);
		return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = userRepository.findById(userDto.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user has email = " + userDto.getId()));
		modelMapper.map(userDto,user);
		user=userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public ResponseMessageDto deleteUser(Long id) {
		 userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user with id = " + id));

		return new ResponseMessageDto(HttpStatus.ACCEPTED, "Deleted user with id= " + id,
				LocalDateTime.now());
	}

	@Override
	public UserDto findUserDtoByEmail(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user has email = " + email));
		return modelMapper.map(user, UserDto.class);
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
