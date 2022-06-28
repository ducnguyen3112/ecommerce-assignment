package com.nashtech.ecommerce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.CategoryDto;
import com.nashtech.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<CategoryDto> findAllCategoryDtos() {
		return categoryService.findAllCategoryDtos();
	}

	@GetMapping("/{id}")
	public CategoryDto findCategoryDtoById(@PathVariable Long id) {
		return categoryService.findCategoryDtoById(id);
	}

	@PostMapping
	public CategoryDto createCategoryDto(@RequestBody CategoryDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}

	@PutMapping
	public CategoryDto updateCategoryDto(@RequestBody CategoryDto categoryDto) {
		return categoryService.updateCategory(categoryDto);
	}
}
