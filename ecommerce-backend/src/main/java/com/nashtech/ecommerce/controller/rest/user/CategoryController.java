package com.nashtech.ecommerce.controller.rest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<ResponseCategoryDto> findAllCategoryDtos() {
		return categoryService.findAllCategoryDtos();
	}

	@GetMapping("/{id}")
	public ResponseCategoryDto findCategoryDtoById(@PathVariable Long id) {
		return categoryService.findCategoryDtoById(id);
	}
}
