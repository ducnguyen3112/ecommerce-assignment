package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.entity.RoleName;

public interface RoleService {

	Role findByName(RoleName roleName);

}
