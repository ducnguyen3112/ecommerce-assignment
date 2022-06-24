package com.nashtech.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.CartDto;
import com.nashtech.ecommerce.entity.Cart;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.CartRepository;
import com.nashtech.ecommerce.service.CartService;
@Service
public class CartServiceImpl implements CartService{
	private CartRepository cartRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CartServiceImpl(CartRepository cartRepository,ModelMapper modelMapper) {
		super();
		this.cartRepository=cartRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public List<CartDto> findAllCartDtos() {
		List<Cart> carts=cartRepository.findAll();	
		return  modelMapper.map(carts, new TypeToken<List<CartDto>>(){}.getType());
	}
	@Override
	public CartDto findCartDtoById(Long id) {
		Optional<Cart> optional=cartRepository.findById(id);
		Cart cart=null;
		if (optional.isPresent()) {
			cart=optional.get();
		}else {
			throw new ResourceNotFoundException("Did not find cart with id = "+id);
		}
		return modelMapper.map(cart , CartDto.class);
	}
	@Override
	public CartDto saveCart(CartDto cartDto) {
		
		cartDto.setId((long) 0);
		cartDto.setCreatedAt(new Date());
		cartDto.setModifiedAt(new Date());
		Cart cart = cartRepository.save(modelMapper.map(cartDto,Cart.class));
		return modelMapper.map(cart , CartDto.class);
	}
	@Override
	public CartDto updateCart(Map<Object, Object> fields,Long id) {
		Optional<Cart> optional=cartRepository.findById(id);
		Cart cart;
		if (optional.isPresent()) {
			cart=optional.get();
			fields.forEach((k, v) -> {
				Field field=ReflectionUtils.findRequiredField(Cart.class, (String) k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, cart, v);
			});
		}else {
			throw new ResourceNotFoundException("Did not find cart with id = "+id);
		}
		cart.setModifiedAt(new Date());
		return modelMapper.map( cart, CartDto.class);
	}
	
}
