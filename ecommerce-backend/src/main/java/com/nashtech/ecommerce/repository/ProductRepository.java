package com.nashtech.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findByStatusAndProductNameContaining(String name, int status,
			Pageable pageable);

	Page<Product> findByProductNameContaining(String name, Pageable pageable);

	Page<Product> findByStatus(int status, Pageable pageable);
}
