package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.entity.Category;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private CategoryServiceImpl categoryServiceImpl;

    private Category category;
    private Optional<Category> categoryOptional;

    @BeforeEach
    void setUp() {

        category = mock(Category.class);
        categoryOptional = Optional.of(category);
        categoryRepository = mock(CategoryRepository.class);
        modelMapper = mock(ModelMapper.class);
        categoryServiceImpl = new CategoryServiceImpl(categoryRepository, modelMapper);

    }

    @Test
    public void findCategoryById_WhenRequestValid_Expect_ReturnResponseCategory() {
        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        ResponseCategoryDto expected = mock(ResponseCategoryDto.class);
        when(modelMapper.map(category, ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual = categoryServiceImpl.findCategory(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findCategory_WhenIdNotFound_Expect_ThrowResourceNotFoundException() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.findCategory(1L));
        assertThat(exception.getMessage()).isEqualTo("Did not find category with id = " + 1L);
    }

    @Test
    public void createCategory_WhenRequestValid_Expect_ReturnCategorySaved() {
        RequestCategoryDto requestCategoryDto = mock(RequestCategoryDto.class);
        when(modelMapper.map(requestCategoryDto, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        ResponseCategoryDto expected = mock(ResponseCategoryDto.class);
        when(modelMapper.map(category, ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual = categoryServiceImpl.createCategory(requestCategoryDto);
        verify(categoryRepository.save(category));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updateCategory_WhenRequestValid_Expert_ReturnCategoryEdited() {
        RequestCategoryDto requestCategoryDto = mock(RequestCategoryDto.class);
        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        when(categoryRepository.save(category)).thenReturn(category);
        ResponseCategoryDto expected = mock(ResponseCategoryDto.class);
        when(modelMapper.map(category, ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual = categoryServiceImpl.updateCategory(requestCategoryDto, 1L);
        verify(modelMapper).map(requestCategoryDto, category);
        assertEquals(expected, actual);
    }
}
