package com.nashtech.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.CategoryDto;
import com.nashtech.ecommerce.entity.Category;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.CategoryRepository;
import com.nashtech.ecommerce.service.CategoryService;
@Service
public class CategoryServiceImpl implements  CategoryService{

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public List<CategoryDto> findAllCategoryDtos() {
		List<Category> categorys=categoryRepository.findAll();	
		return  modelMapper.map(categorys, new TypeToken<List<CategoryDto>>(){}.getType());
	}
	@Override
	public CategoryDto findCategoryDtoById(Long id) {
		Optional<Category> optional=categoryRepository.findById(id);
		Category category=null;
		if (optional.isPresent()) {
			category=optional.get();
		}else {
			throw new ResourceNotFoundException("Did not find category with id = "+id);
		}
		return modelMapper.map(category , CategoryDto.class);
	}
	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		
		categoryDto.setId((long) 0);
		Category category = categoryRepository.save(modelMapper.map(categoryDto,Category.class));
		return modelMapper.map(category , CategoryDto.class);
	}
	@Override
	public CategoryDto updateCategory(Map<Object, Object> fields,Long id) {
		Optional<Category> optional=categoryRepository.findById(id);
		Category category;
		if (optional.isPresent()) {
			category=optional.get();
			fields.forEach((k, v) -> {
				Field field=ReflectionUtils.findRequiredField(Category.class, (String) k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, category, v);
			});
		}else {
			throw new ResourceNotFoundException("Did not find category with id = "+id);
		}
		return modelMapper.map( category, CategoryDto.class);
	}
}
