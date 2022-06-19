package com.nashtech.ecommerce.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.entity.CartItem;
import com.nashtech.ecommerce.repository.CartItemRepository;


@RestController
@RequestMapping("/cartItems")
public class CartItemController {
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@GetMapping
	public List<CartItem> findAllUserDtos() {
		return cartItemRepository.findAll();
	}
	

}
