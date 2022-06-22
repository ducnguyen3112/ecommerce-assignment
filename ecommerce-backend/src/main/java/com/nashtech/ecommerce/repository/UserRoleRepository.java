package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.UserRole;
import com.nashtech.ecommerce.entity.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
