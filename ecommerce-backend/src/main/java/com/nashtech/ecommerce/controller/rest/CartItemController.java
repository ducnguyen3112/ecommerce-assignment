package com.nashtech.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.CartDto;
import com.nashtech.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
	@Autowired
	private CartService cartService;

	@GetMapping("{/id}")
	public CartDto findCartDtoById(@PathVariable Long id) {
		return cartService.findCartDtoById(id);
	}

}
