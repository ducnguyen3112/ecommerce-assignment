package com.nashtech.ecommerce.controller.rest.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.service.ProductService;

@RestController
@RequestMapping("/admin/products")
public class ProductAdminController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseListProduct findAllProductDtos(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "status", required = false) String status,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		ProductStatus productStatus = null;
		if (status!=null) {
			if (status.equalsIgnoreCase("STOCKING")) {
				productStatus = ProductStatus.STOCKING;
			} else if (status.equalsIgnoreCase("STOCKING")) {
				productStatus = ProductStatus.OUT_OF_STOCK;
			} else {
				throw new TypeMismatchException("status must be STOCKING or OUT_OF_STOCK ");
			}
		}else {
			productStatus=null;
		}
		return productService.findAllProduct(name, productStatus, currentPage, pageSize);
	}

	@GetMapping("/{id}")
	public ResponseProductDto findProductDtoById(@PathVariable Long id) {
		return productService.findProductDtoById(id);
	}

	@PostMapping
	public ResponseProductDto createProductDto(
			@Valid @RequestBody RequestProductDto productDto) {
		return productService.createProduct(productDto);
	}

	@PutMapping
	public ResponseProductDto updateProductDto(
			@Valid @RequestBody RequestProductDto productDto) {
		return productService.updateProduct(productDto);
	}

	@DeleteMapping("/{id}")
	public ResponseMessageDto deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
}
