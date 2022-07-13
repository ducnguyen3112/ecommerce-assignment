package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {

    List<ResponseCategoryDto> findAllCategory();

    ResponseCategoryDto findCategoryById(Long id);

    ResponseCategoryDto createCategory(RequestCategoryDto categoryDto);

    ResponseCategoryDto updateCategory(RequestCategoryDto categoryDto, Long id);
}
