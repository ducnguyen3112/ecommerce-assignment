package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Long id;
	private String categoryName;
	private String description;
	private Category parentCategory;
}
