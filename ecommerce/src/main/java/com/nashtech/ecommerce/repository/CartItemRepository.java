package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.CartItem;
import com.nashtech.ecommerce.entity.CartItemId;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{

}
