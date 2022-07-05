package com.nashtech.ecommerce.controller.rest.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;
import com.nashtech.ecommerce.service.ProductService;
import com.nashtech.ecommerce.service.RatingService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private RatingService ratingService;

	@GetMapping
	public ResponseListProduct findAllProductDtos(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("status") Optional<Integer> statusOptional,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		int status = statusOptional.orElse(-1);
		return productService.findAllProduct(name, status, currentPage, pageSize);
	}

	@GetMapping("/{id}")
	public ResponseProductDto findProductDtoById(@PathVariable Long id) {
		return productService.findProductDtoById(id);
	}

	@PostMapping("/{id}/rating")
	public ResponseRatingDto addRatingForProduct(@PathVariable Long id,
			@RequestBody RequestRatingDto requestRatingDto) {
		return ratingService.addRating(requestRatingDto, id) ;
	}

}
