package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.OrderItem;
import com.nashtech.ecommerce.entity.OrderItemId;

public interface OderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
