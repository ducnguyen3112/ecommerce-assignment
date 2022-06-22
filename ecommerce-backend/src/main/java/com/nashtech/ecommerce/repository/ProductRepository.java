package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
