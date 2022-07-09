package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.CartItem;
import com.nashtech.ecommerce.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

}
