package com.nashtech.ecommerce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.ProductDto;
import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDto> findAllProductDtos() {
		return productService.findAllProductDtos();
	}

	@GetMapping("/{id}")
	public ProductDto findProductDtoById(@PathVariable Long id) {
		return productService.findProductDtoById(id);
	}

	@PostMapping
	public ProductDto createProductDto(@RequestBody ProductDto productDto) {
		return productService.createProduct(productDto);
	}

	@PutMapping
	public ProductDto updateProductDto(@RequestBody ProductDto productDto) {
		return productService.updateProduct(productDto);
	}

	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
}
