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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    void setUp(){
        categoryRepository= mock(CategoryRepository.class);
        modelMapper=mock(ModelMapper.class);
        categoryServiceImpl=new CategoryServiceImpl(categoryRepository,modelMapper);

    }
    @Test
    public void findCategoryById_WhenRequestValid_Expect_ReturnResponseCategory(){
        Category category=mock(Category.class);
        Optional<Category> categoryOptional=Optional.of(category);
        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        category=categoryOptional.get();
        ResponseCategoryDto expected= mock(ResponseCategoryDto.class);
        when(modelMapper.map(category,ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual=categoryServiceImpl.findCategoryById(1L);
        assertEquals(expected,actual);
    }
    @Test
    public void findCategoryById_WhenIdNotFound_Expect_ThrowResourceNotFoundException(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,() -> categoryServiceImpl.findCategoryById(1L)
                ,"Did not find category with id = " + 1L);
    }
    @Test
    public void createCategory_WhenRequestValid_Expect_ReturnCategorySaved(){
        Category category=mock(Category.class);
        RequestCategoryDto requestCategoryDto=mock(RequestCategoryDto.class);
        when(modelMapper.map(requestCategoryDto,Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        ResponseCategoryDto expected=mock(ResponseCategoryDto.class);
        when(modelMapper.map(category,ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual=categoryServiceImpl.createCategory(requestCategoryDto);
        assertEquals(expected,actual);
    }@Test
    public void updateCategory_WhenRequestValid_Expert_ReturnCategoryEdited(){
        Category category=mock(Category.class);
        RequestCategoryDto requestCategoryDto=mock(RequestCategoryDto.class);
        Optional<Category> categoryOptional=Optional.of(category);
        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        category=categoryOptional.get();
        modelMapper.map(requestCategoryDto,category);
        verify(modelMapper).map(requestCategoryDto,category);
        when(categoryRepository.save(category)).thenReturn(category);
        ResponseCategoryDto expected=mock(ResponseCategoryDto.class);
        when(modelMapper.map(category,ResponseCategoryDto.class)).thenReturn(expected);
        ResponseCategoryDto actual=categoryServiceImpl.updateCategory(requestCategoryDto,1L);
        assertEquals(expected,actual);
    }
}
