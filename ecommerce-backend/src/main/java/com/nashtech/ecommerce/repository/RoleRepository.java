package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
