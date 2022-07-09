package com.nashtech.ecommerce.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCategoryDto {

    private Long id;

    private String categoryName;

    private String description;

    private List<ResponseCategoryDto> childCategories;
}
