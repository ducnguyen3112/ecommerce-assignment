package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestCreateProductDto;
import com.nashtech.ecommerce.dto.request.RequestProductDto;
import com.nashtech.ecommerce.dto.response.ResponseProductDto;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import com.nashtech.ecommerce.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {

        productRepository = mock(ProductRepository.class);
        RatingRepository ratingRepository = mock(RatingRepository.class);
        modelMapper = mock(ModelMapper.class);
        productServiceImpl = new ProductServiceImpl(productRepository, modelMapper, ratingRepository);
    }

    @Test
    public void createProduct_WhenRequestValid_Expect_ReturnProductAfterSave() {
        RequestCreateProductDto requestCreateProductDto = mock(RequestCreateProductDto.class);
        requestCreateProductDto.setStatus(ProductStatus.STOCKING);
        verify(requestCreateProductDto).setStatus(ProductStatus.STOCKING);
        Date date=mock(Date.class);
        Product product = mock(Product.class);
        product.setCreatedAt(date);
        product.setModifiedAt(date);
        verify(product).setCreatedAt(date);
        verify(product).setModifiedAt(date);
        when(modelMapper.map(requestCreateProductDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        ResponseProductDto  expectedProductDto=modelMapper.map(product,ResponseProductDto.class);
        ResponseProductDto actualProductDto= productServiceImpl.createProduct(requestCreateProductDto);

        assertEquals(expectedProductDto,actualProductDto);
    }
    @Test
    public void findProductById_WhenRequestValid_Expect_ReturnProduct(){
        Product product=mock(Product.class);
        Optional<Product> productOptional=Optional.of(product);
        ResponseProductDto productDto=mock(ResponseProductDto.class);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        when(modelMapper.map(productOptional.get(),ResponseProductDto.class)).thenReturn(productDto);
        assertEquals(productDto, productServiceImpl.findProductById(1L));
    }
    @Test
    public void findUserById_WhenIdNotFound_Expect_throwResourceNotFoundException(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,() -> productServiceImpl.findProductById(1L),"Did not find product has id = " + 1L);
    }


    @Test
    public void updateProduct_WhenRequestValid_Expect_ReturnProductAfterUpdated(){
        Product product=mock(Product.class);
        Optional<Product> productOptional=Optional.of(product);
        Date date=mock(Date.class);
        RequestProductDto requestProductDto=mock(RequestProductDto.class);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        product=productOptional.get();
        modelMapper.map(requestProductDto,product);
        product.setModifiedAt(date);
        verify(product).setModifiedAt(date
        );
        when(productRepository.save(product)).thenReturn(product);
        ResponseProductDto expectedProductDto=modelMapper.map(product,ResponseProductDto.class);
        ResponseProductDto actualProductDto= productServiceImpl.updateProduct(requestProductDto,1L);
        assertEquals(expectedProductDto,actualProductDto);

    }
    @Test
    public void deleteProduct_WhenRequestValid_Expect_ReturnResponseProduct(){
        Product product=mock(Product.class);
        Optional<Product> productOptional=Optional.of(product);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        product=productOptional.get();
        product.setStatus(ProductStatus.OUT_OF_STOCK);
        verify(product).setStatus(ProductStatus.OUT_OF_STOCK);
        ResponseProductDto expectedProduct=mock(ResponseProductDto.class);
        when(modelMapper.map(product,ResponseProductDto.class)).thenReturn(expectedProduct);
        ResponseProductDto actualProduct= productServiceImpl.deleteProduct(1L);
        assertEquals(expectedProduct,actualProduct);

    }
}
