package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.CategoryDto;

public interface CategoryService {

	List<CategoryDto> findAllCategoryDtos();

	CategoryDto findCategoryDtoById(Long id);

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto);

}
