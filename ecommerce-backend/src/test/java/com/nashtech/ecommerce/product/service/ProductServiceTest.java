package com.nashtech.ecommerce.product.service;

import com.nashtech.ecommerce.dto.request.RequestCreateProductDto;
import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.repository.ProductRepository;
import com.nashtech.ecommerce.repository.RatingRepository;
import com.nashtech.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductServiceImpl productService;

    private RatingRepository ratingRepository;

    Optional<Product>  productOptional = null;

    @BeforeEach
    void setUp() {
        Product product=new Product();
        product.setId(1l);
        productOptional=Optional.of(product);
        productRepository = mock(ProductRepository.class);
        ratingRepository = mock(RatingRepository.class);
        modelMapper = mock(ModelMapper.class);
        productService = new ProductServiceImpl(productRepository, modelMapper, ratingRepository);
    }

    @Test
    public void createProduct_WhenRequestValid_Expect_ReturnProductAfterSave() {
        RequestCreateProductDto requestCreateProductDto = mock(RequestCreateProductDto.class);
        Product product = mock(Product.class);
        when(modelMapper.map(requestCreateProductDto, Product.class)).thenReturn(product);
        product.setStatus(ProductStatus.STOCKING);
        product.setCreatedAt(new Date());
        product.setModifiedAt(new Date());
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(modelMapper.map(product,ResponseProductDto.class),productService.createProduct(requestCreateProductDto));
    }
    @Test
    public void findProductById_WhenRequestValid_Expect_ReturnProduct(){
        ResponseProductDto productDto=mock(ResponseProductDto.class);
        when(productRepository.findById(anyLong())).thenReturn(productOptional);
        when(modelMapper.map(productOptional.get(),ResponseProductDto.class)).thenReturn(productDto);
        assertEquals(productDto,productService.findProductById(anyLong()));
    }


    @Test
    public void updateProduct_WhenRequestValid_Expect_ReturnProductAfterUpdate(){
        RequestProductDto requestProductDto=mock(RequestProductDto.class);
        when(productRepository.findById(anyLong())).thenReturn(productOptional);
        modelMapper.map(requestProductDto,productOptional.get());
        productOptional.get().setModifiedAt(new Date());
        Product product=mock(Product.class);
        when(productRepository.save(productOptional.get())).thenReturn(product);
        assertEquals(modelMapper.map(product,ResponseProductDto.class),productService.updateProduct(requestProductDto,anyLong()));

    }

}
