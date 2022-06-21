package com.nashtech.ecommerce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.entity.CartItem;
import com.nashtech.ecommerce.repository.CartItemRepository;


@RestController
@RequestMapping("/cart-items")
public class CartItemController {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@GetMapping
	public List<CartItem> findAllUserDtos() {
		return cartItemRepository.findAll();
	}
	

}
