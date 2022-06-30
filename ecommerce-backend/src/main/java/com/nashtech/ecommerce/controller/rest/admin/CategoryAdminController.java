package com.nashtech.ecommerce.controller.rest.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/admin/categories")
public class CategoryAdminController {
	
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

	@PostMapping
	public ResponseCategoryDto createCategoryDto(@Valid @RequestBody RequestCategoryDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}

	@PutMapping
	public ResponseCategoryDto updateCategoryDto(@Valid @RequestBody RequestCategoryDto categoryDto) {
		return categoryService.updateCategory(categoryDto);
	}
}
