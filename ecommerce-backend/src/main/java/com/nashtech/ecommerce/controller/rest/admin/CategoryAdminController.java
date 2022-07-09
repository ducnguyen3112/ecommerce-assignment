package com.nashtech.ecommerce.controller.rest.admin;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCategoryDto> findAllCategoryDtos() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDto findCategoryDtoById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto createCategoryDto(
            @Valid @RequestBody RequestCategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDto updateCategoryDto(
            @Valid @RequestBody RequestCategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }
}
