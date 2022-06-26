package com.nashtech.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce.dto.CategoryDto;

public interface CategoryService {

	List<CategoryDto> findAllCategoryDtos();

	CategoryDto findCategoryDtoById(Long id);

	CategoryDto saveCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(Map<Object, Object> fields, Long id);

}
