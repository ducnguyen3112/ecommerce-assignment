package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.CartDto;

public interface CartService {

	CartDto findCartDtoById(Long id);

	CartDto createCart(CartDto cartDto);

	CartDto updateCart(CartDto cartDto);

}
