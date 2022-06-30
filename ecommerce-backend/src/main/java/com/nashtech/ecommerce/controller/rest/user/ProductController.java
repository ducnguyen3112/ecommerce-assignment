package com.nashtech.ecommerce.controller.rest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ResponseProductDto> findAllProductDtos() {
		return productService.findAllProductDtos();
	}

	@GetMapping("/{id}")
	public ResponseProductDto findProductDtoById(@PathVariable Long id) {
		return productService.findProductDtoById(id);
	}


}
