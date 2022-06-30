package com.nashtech.ecommerce.service;

import java.util.List;

import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;

public interface ProductService {

	List<ResponseProductDto> findAllProductDtos();

	ResponseProductDto findProductDtoById(Long id);

	ResponseMessageDto deleteProduct(Long id);

	ResponseProductDto createProduct(RequestProductDto productDto);

	ResponseProductDto updateProduct(RequestProductDto productDto);

}
