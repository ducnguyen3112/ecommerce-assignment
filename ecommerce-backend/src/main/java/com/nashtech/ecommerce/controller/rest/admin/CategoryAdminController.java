package com.nashtech.ecommerce.controller.rest.admin;

import com.nashtech.ecommerce.dto.request.RequestCategoryDto;
import com.nashtech.ecommerce.dto.response.ResponseCategoryDto;
import com.nashtech.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Find all categories in admin site",
            tags = {"Administrator"})
    public List<ResponseCategoryDto> findAllCategoryDtos() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a category by id in admin site",
            tags = {"Administrator"})
    public ResponseCategoryDto findCategoryDtoById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category",
            tags = {"Administrator"})
    public ResponseCategoryDto createCategoryDto(
            @Valid @RequestBody RequestCategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit a category",
            tags = {"Administrator"})
    public ResponseCategoryDto updateCategoryDto(
            @Valid @RequestBody RequestCategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }
}
