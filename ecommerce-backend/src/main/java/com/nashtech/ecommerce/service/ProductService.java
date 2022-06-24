package com.nashtech.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce.dto.ProductDto;
import com.nashtech.ecommerce.dto.ResponseMessageDto;

public interface ProductService {

	List<ProductDto> findAllProductDtos();

	ProductDto findProductDtoById(Long id);

	ProductDto saveProduct(ProductDto ProductDto);

	ProductDto updateProduct(Map<Object, Object> fields, Long id);

	ResponseMessageDto deleteProduct(Long id);

}
