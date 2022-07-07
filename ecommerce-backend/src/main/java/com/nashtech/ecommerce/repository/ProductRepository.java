package com.nashtech.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findByStatusAndProductNameContaining(String name, ProductStatus status,
			Pageable pageable);

	Page<Product> findByProductNameContaining(String name, Pageable pageable);

	Page<Product> findByStatus(ProductStatus status, Pageable pageable);
}
