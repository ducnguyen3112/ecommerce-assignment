package com.nashtech.ecommerce.controller.rest.user;

import com.nashtech.ecommerce.dto.response.ResponseCartDto;
import com.nashtech.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
    private final CartService cartService;
    @Autowired
    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCartDto findCartDtoById(@PathVariable Long id) {
        return cartService.findCartById(id);
    }

}
