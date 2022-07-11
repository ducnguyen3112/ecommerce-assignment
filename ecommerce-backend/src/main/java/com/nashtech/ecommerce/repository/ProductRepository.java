package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByStatusAndProductNameContaining(String name, ProductStatus status,
                                                       Pageable pageable);

    Page<Product> findByProductNameContaining(String name, Pageable pageable);

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    List<Product> findTop8ByOrderByCreatedAtDesc();
}
