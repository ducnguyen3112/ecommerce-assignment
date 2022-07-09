package com.nashtech.ecommerce.dto.request;

import com.nashtech.ecommerce.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestCreateProductDto {

    @NotBlank
    private String productName;

    private String description;

    private int inventory;

    private Long price;

    private ProductStatus status;

    private String image;
}
