package com.nashtech.ecommerce.controller.rest.user;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.service.ProductService;
import com.nashtech.ecommerce.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@Tag(name = "product data for users ",
        description = "display and allow  rating products")
public class ProductController {

    private final ProductService productService;
    private final RatingService ratingService;
    @Autowired
    public ProductController(ProductService productService, RatingService ratingService) {
        this.productService = productService;
        this.ratingService = ratingService;
    }

    @GetMapping
    @Operation(summary = "Get all products are stocking and paginate")
    public ResponseListProduct findAllProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("status") Optional<ProductStatus> statusOptional,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        ProductStatus status = statusOptional.orElse(null);
        return productService.findAllProduct(name, status, currentPage, pageSize);
    }

    @Operation(summary = "Get a product with id")
    @GetMapping("/{id}")
    public ResponseProductDto findProductDtoById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("/{id}/rating")
    @Operation(summary = "Rating product")
    public ResponseRatingDto addRatingForProduct(@PathVariable Long id,
                                                 @RequestBody RequestRatingDto requestRatingDto) {
        return ratingService.addRating(requestRatingDto, id);
    }

}
