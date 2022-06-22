package com.nashtech.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
