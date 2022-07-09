package com.nashtech.ecommerce.controller.rest.user;

import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCategoryDto> findAllCategory() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDto findCategoryDtoById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }
}
