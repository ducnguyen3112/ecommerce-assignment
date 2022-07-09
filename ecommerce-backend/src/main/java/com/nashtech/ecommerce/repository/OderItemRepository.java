package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.OrderItem;
import com.nashtech.ecommerce.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
