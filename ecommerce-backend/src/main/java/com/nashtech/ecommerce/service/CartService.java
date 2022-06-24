package com.nashtech.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.nashtech.ecommerce.dto.CartDto;

public interface CartService {

	List<CartDto> findAllCartDtos();

	CartDto findCartDtoById(Long id);

	CartDto saveCart(CartDto cartDto);

	CartDto updateCart(Map<Object, Object> fields, Long id);

}
