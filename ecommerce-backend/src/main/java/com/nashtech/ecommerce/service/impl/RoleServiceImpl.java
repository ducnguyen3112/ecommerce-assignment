package com.nashtech.ecommerce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.RoleRepository;
import com.nashtech.ecommerce.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public Role findRoleDtoByName(String roleName) {
		Role role = roleRepository.findByRoleName(roleName).orElseThrow(
				() -> new ResourceNotFoundException("Did not find role with = " + roleName));
		return role;
	}
}
