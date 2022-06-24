package com.nashtech.ecommerce.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.RoleRepository;
import com.nashtech.ecommerce.service.RoleService;

public class RoleServiceImpl implements RoleService{
	
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
		super();
		this.roleRepository = roleRepository;
	}
	@Override
	public Role findRoleDtoByName(String roleName) {
		Optional<Role> optional=roleRepository.findByRoleName(roleName);
		Role role=null;
		if (optional.isPresent()) {
			role=optional.get();
		}else {
			throw new ResourceNotFoundException("Did not find role with name = "+roleName);
		}
		return role;
	}
}
