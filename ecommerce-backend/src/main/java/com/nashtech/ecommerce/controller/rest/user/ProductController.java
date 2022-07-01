package com.nashtech.ecommerce.controller.rest.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseListProduct findAllProductDtos(
			@RequestParam("name") String productName,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(10);
		int status=1;
		return productService.findAllProduct(productName,status,currentPage, pageSize);
	}

	@GetMapping("/{id}")
	public ResponseProductDto findProductDtoById(@PathVariable Long id) {
		return productService.findProductDtoById(id);
	}

}
