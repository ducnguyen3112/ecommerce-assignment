package com.nashtech.ecommerce.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.entity.Category;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.CategoryRepository;
import com.nashtech.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository,
			ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ResponseCategoryDto> findAllCategoryDtos() {
		List<Category> categorys = categoryRepository.findAll();
		return modelMapper.map(categorys, new TypeToken<List<ResponseCategoryDto>>() {
		}.getType());
	}

	@Override
	public ResponseCategoryDto findCategoryDtoById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Did not find category with id = " + id));
		return modelMapper.map(category, ResponseCategoryDto.class);
	}

	@Override
	public ResponseCategoryDto createCategory(RequestCategoryDto categoryDto) {
		Category category = categoryRepository
				.save(modelMapper.map(categoryDto, Category.class));
		return modelMapper.map(category, ResponseCategoryDto.class);
	}

	@Override
	public ResponseCategoryDto updateCategory(RequestCategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Did not find category with id = " + categoryDto.getId()));
		modelMapper.map(categoryDto, category);
		category = categoryRepository.save(category);
		return modelMapper.map(category, ResponseCategoryDto.class);
	}
}
