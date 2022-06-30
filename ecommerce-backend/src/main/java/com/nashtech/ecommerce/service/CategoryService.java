package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;

public interface CategoryService {

	List<ResponseCategoryDto> findAllCategoryDtos();

	ResponseCategoryDto findCategoryDtoById(Long id);

	ResponseCategoryDto createCategory(RequestCategoryDto categoryDto);

	ResponseCategoryDto updateCategory(RequestCategoryDto categoryDto);

}
