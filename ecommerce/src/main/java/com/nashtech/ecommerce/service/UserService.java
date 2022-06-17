package com.nashtech.ecommerce.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.UserDto;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	public List<UserDto> findAllUserDtos() {
		List<User> userList=userRepository.findAll();	
		return  modelMapper.map(userList, new TypeToken<List<UserDto>>(){}.getType());
	}
}
