package com.nashtech.ecommerce.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCategoryDto {

    private Long id;

    private String categoryName;

    private String description;

}
