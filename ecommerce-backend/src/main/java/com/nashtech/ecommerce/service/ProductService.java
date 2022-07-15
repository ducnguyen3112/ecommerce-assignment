package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestCreateProductDto;
import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.enums.ProductStatus;

public interface ProductService {

    ResponseListProduct getAllProduct(String productName, ProductStatus status, int page, int size, Long categoryId);

    ResponseProductDto getProduct(Long id);

    ResponseProductDto deleteProduct(Long id);

    ResponseProductDto createProduct(RequestCreateProductDto productDto);

    ResponseProductDto updateProduct(RequestProductDto productDto, Long id);


    ResponseListProduct getFeaturedProducts();

}
