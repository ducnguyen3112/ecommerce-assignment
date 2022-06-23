package com.nashtech.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	public List<UserDto> findAllUserDtos() {
		List<User> userList=userRepository.findAll();	
		return  modelMapper.map(userList, new TypeToken<List<UserDto>>(){}.getType());
	}
	@Override
	public UserDto findUserDtoById(Long id) {
		Optional<User> optional=userRepository.findById(id);
		User user=null;
		if (optional.isPresent()) {
			user=optional.get();
		}else {
			throw new ResourceNotFoundException("Did not find user with id = "+id);
		}
		return modelMapper.map(user , UserDto.class);
	}
	@Override
	public UserDto saveUser(UserDto userDto) {
		User user=null;
		user= userRepository.save(modelMapper.map(userDto,User.class));
		return modelMapper.map(user , UserDto.class);
	}
	@Override
	public UserDto updateUser(Map<Object, Object> fields,Long id) {
		Optional<User> optional=userRepository.findById(id);
		User user;
		if (optional.isPresent()) {
			user=optional.get();
			fields.forEach((k, v) -> {
				Field field=ReflectionUtils.findRequiredField(User.class, (String) k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, user, v);
			});
		}else {
			throw new ResourceNotFoundException("Did not find user with id = "+id);
		}
		return modelMapper.map( user, UserDto.class);
	}
	@Override
	public ResponseMessageDto delelteUser(Long id) {
		Optional<User> optional=userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Did not find user with id = "+id);
		}
		userRepository.deleteById(id);
		return new ResponseMessageDto(HttpStatus.ACCEPTED, "Deleted user with id= "+id, LocalDateTime.now());
	}
}
