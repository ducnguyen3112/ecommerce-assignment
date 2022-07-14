package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestCreateProductDto;
import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseListProduct;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import com.nashtech.ecommerce.repository.RatingRepository;
import com.nashtech.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final RatingRepository ratingRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper, RatingRepository ratingRepository) {
        super();
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public ResponseListProduct findAllProduct(String productName, ProductStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productPage;
        if (status == null) {
            if (StringUtils.hasText(productName)) {
                productPage = productRepository.findByProductNameContaining(productName, pageable);
            } else {
                productPage = productRepository.findAll(pageable);
            }
        } else {
            if (StringUtils.hasText(productName)) {
                productPage = productRepository.findByStatusAndProductNameContaining(productName, status, pageable);
            } else {
                productPage = productRepository.findByStatus(status, pageable);
            }
        }
        List<Product> products = productPage.getContent();
        List<ResponseProductDto> responseProductDtos = modelMapper.map(products,
                new TypeToken<List<ResponseProductDto>>() {
                }.getType());
        return ResponseListProduct.builder().totalProduct(productPage.getTotalElements())
                .perPage(productPage.getNumberOfElements())
                .currentPage(productPage.getNumber() + 1)
                .lastPage(productPage.getTotalPages())
                .productDtos(responseProductDtos)
                .build();
    }

    @Override
    public ResponseProductDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find product with id = " + id));
        ResponseProductDto responseProductDto = modelMapper.map(product, ResponseProductDto.class);
        responseProductDto.setAvgRating(ratingRepository.findAVGRatingOfProduct(responseProductDto.getId()).orElse(0f));
        return responseProductDto;
    }

    @Override
    public ResponseProductDto createProduct(RequestCreateProductDto productDto) {
        productDto.setStatus(ProductStatus.STOCKING);
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.now());
        product.setModifiedAt(LocalDateTime.now());
        product.setAvgRating(0F);
        productRepository
                .save(modelMapper.map(productDto, Product.class));

        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public ResponseProductDto updateProduct(RequestProductDto productDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find product has id = " + id));
        modelMapper.map(productDto, product);
        product.setModifiedAt(LocalDateTime.now());
        product = productRepository.save(product);
        return modelMapper.map(product, ResponseProductDto.class);
    }


    @Override
    public ResponseProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Did not find product with id = " + id));
        product.setStatus(ProductStatus.OUT_OF_STOCK);
        return modelMapper.map(product, ResponseProductDto.class);
    }

    @Override
    public ResponseListProduct findFeaturedProducts() {
        List<Product> products = productRepository.findTop8ByOrderByCreatedAtDesc();
        List<ResponseProductDto> responseProductDtos = modelMapper.map(products,
                new TypeToken<List<ResponseProductDto>>() {
                }.getType());
        return ResponseListProduct.builder().totalProduct((long) responseProductDtos.size())
                .currentPage(1)
                .perPage(responseProductDtos.size())
                .productDtos(responseProductDtos)
                .lastPage(1)
                .build();
    }

}
