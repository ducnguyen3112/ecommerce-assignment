package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.enums.ProductStatus;

public interface ProductService {

	ResponseProductDto findProductDtoById(Long id);

	ResponseMessageDto deleteProduct(Long id);

	ResponseProductDto createProduct(RequestProductDto productDto);

	ResponseProductDto updateProduct(RequestProductDto productDto);
	
	ResponseListProduct findAllProduct(String productName, ProductStatus status, int page,
			int size);

	

}
