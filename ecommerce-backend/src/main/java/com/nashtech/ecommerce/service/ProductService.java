package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.ProductDto;
import com.nashtech.ecommerce.dto.ResponseMessageDto;

public interface ProductService {

	List<ProductDto> findAllProductDtos();

	ProductDto findProductDtoById(Long id);

	ResponseMessageDto deleteProduct(Long id);

	ProductDto createProduct(ProductDto productDto);

	ProductDto updateProduct(ProductDto productDto);

}
