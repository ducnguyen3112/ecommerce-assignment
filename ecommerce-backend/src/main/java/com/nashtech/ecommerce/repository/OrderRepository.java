package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
