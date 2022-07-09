package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestCartDto;
import com.nashtech.ecommerce.dto.response.ResponseCartDto;
import com.nashtech.ecommerce.entity.Cart;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.CartRepository;
import com.nashtech.ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        super();
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseCartDto findCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Did not find cart with id = " + id));
        return modelMapper.map(cart, ResponseCartDto.class);
    }

    @Override
    public ResponseCartDto createCart(RequestCartDto cartDto) {
        cartDto.setCreatedAt(new Date());
        cartDto.setModifiedAt(new Date());
        Cart cart = cartRepository.save(modelMapper.map(cartDto, Cart.class));
        return modelMapper.map(cart, ResponseCartDto.class);
    }

    @Override
    public ResponseCartDto updateCart(RequestCartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find cart with id = " + cartDto.getId()));
        modelMapper.map(cartDto, cart);
        cart = cartRepository.save(cart);
        return modelMapper.map(cart, ResponseCartDto.class);
    }

}
