package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestCartDto;
import com.nashtech.ecommerce.dto.response.ResponseCartDto;

public interface CartService {

    ResponseCartDto findCartById(Long id);

    ResponseCartDto createCart(RequestCartDto cartDto);

    ResponseCartDto updateCart(RequestCartDto cartDto);

}
