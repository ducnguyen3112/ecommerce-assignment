package com.nashtech.ecommerce.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto saveProductDto(@RequestBody ProductDto productDto) {
		return productService.saveProduct(productDto);
	}
	@PatchMapping("/{id}")
	public ProductDto updateProductDto(@RequestBody Map<Object, Object> fields, @PathVariable Long id) {
		return productService.updateProduct(fields, id);
	}
	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
}
