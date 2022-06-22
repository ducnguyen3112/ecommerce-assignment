package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
