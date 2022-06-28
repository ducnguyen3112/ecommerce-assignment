package com.nashtech.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Did not find user has email = " + email));
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		user.getUserRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), authorities);
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
	public UserDto createUser(User user) {

		user.setId((long) 0);
		user.setRegisteredAt(new Date());
		user.setStatus(1);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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

}
