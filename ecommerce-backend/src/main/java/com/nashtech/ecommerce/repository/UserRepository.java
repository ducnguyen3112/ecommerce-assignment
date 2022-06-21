package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
