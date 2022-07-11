package com.nashtech.ecommerce.controller.rest.admin;

import com.nashtech.ecommerce.dto.request.RequestCreateProductDto;
import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/admin/products")
//@Tag(name = 
//        description = "Allow to display and edit product data")
public class ProductAdminController {
    private final ProductService productService;
    @Autowired
    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products and paginate including out-of-stock products",
    tags = {"Administrator"})
    public ResponseListProduct findAllProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        ProductStatus productStatus;
        if (status != null) {
            if (status.equalsIgnoreCase("STOCKING")) {
                productStatus = ProductStatus.STOCKING;
            } else if (status.equalsIgnoreCase("OUT_OF_STOCK")) {
                productStatus = ProductStatus.OUT_OF_STOCK;
            } else {
                throw new TypeMismatchException("status must be STOCKING or OUT_OF_STOCK ");
            }
        } else {
            productStatus = null;
        }
        return productService.findAllProduct(name, productStatus, currentPage, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Show product details",
            tags = {"Administrator"})
    public ResponseProductDto findProductDtoById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new product",
            tags = {"Administrator"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDto createProductDto(
            @Valid @RequestBody RequestCreateProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit an existing product",
            tags = {"Administrator"})
    public ResponseProductDto updateProductDto(
            @Valid @RequestBody RequestProductDto productDto, @PathVariable Long id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change status of product stocking of out-of-stock",
            tags = {"Administrator"})
    public ResponseMessageDto deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
