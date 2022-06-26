package com.nashtech.ecommerce.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.CategoryDto;
import com.nashtech.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
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
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto saveCategoryDto(@RequestBody CategoryDto categoryDto) {
		return categoryService.saveCategory(categoryDto);
	}
	@PatchMapping("/{id}")
	public CategoryDto updateCategoryDto(@RequestBody Map<Object, Object> fields, @PathVariable Long id) {
		return categoryService.updateCategory(fields, id);
	}
}
