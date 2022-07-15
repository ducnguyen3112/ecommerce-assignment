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
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    Product product;
    Optional<Product> productOptional;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        product = mock(Product.class);
        productOptional = Optional.of(product);
        productRepository = mock(ProductRepository.class);
        RatingRepository ratingRepository = mock(RatingRepository.class);
        modelMapper = mock(ModelMapper.class);
        productServiceImpl = new ProductServiceImpl(productRepository, modelMapper, ratingRepository);
    }

    @Test
    public void createProduct_WhenRequestValid_Expect_ReturnProductAfterSave() {
        RequestCreateProductDto requestCreateProductDto = mock(RequestCreateProductDto.class);
        LocalDateTime time=LocalDateTime.now();
        when(modelMapper.map(requestCreateProductDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        ResponseProductDto expectedProductDto = modelMapper.map(product, ResponseProductDto.class);
        ResponseProductDto actualProductDto = productServiceImpl.createProduct(requestCreateProductDto);
        ArgumentCaptor<LocalDateTime> localDateTimeCaptor=ArgumentCaptor.forClass(LocalDateTime.class);
        verify(product).setCreatedAt(localDateTimeCaptor.capture());
        verify(product).setModifiedAt(localDateTimeCaptor.capture());
        assertTrue(time.isBefore(localDateTimeCaptor.getValue())||time.isEqual(localDateTimeCaptor.getValue()));
        verify(product).setStatus(ProductStatus.STOCKING);
        assertThat(actualProductDto).isEqualTo(expectedProductDto);
    }

    @Test
    public void getProduct_WhenRequestValid_Expect_ReturnProduct() {

        ResponseProductDto expected = mock(ResponseProductDto.class);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        when(modelMapper.map(productOptional.get(), ResponseProductDto.class)).thenReturn(expected);
        ResponseProductDto actual = productServiceImpl.getProduct(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getProduct_WhenIdNotFound_Expect_throwResourceNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProduct(1L));
        assertThat(exception.getMessage()).isEqualTo("Did not find product has id = " + 1L);
    }


    @Test
    public void updateProduct_WhenRequestValid_Expect_ReturnProductAfterUpdated() {
        RequestProductDto requestProductDto = mock(RequestProductDto.class);
        LocalDateTime time=LocalDateTime.now();
        when(productRepository.findById(1L)).thenReturn(productOptional);
        modelMapper.map(requestProductDto, product);
        when(productRepository.save(product)).thenReturn(product);
        ResponseProductDto expectedProductDto = modelMapper.map(product, ResponseProductDto.class);
        ResponseProductDto actualProductDto = productServiceImpl.updateProduct(requestProductDto, 1L);
        ArgumentCaptor<LocalDateTime> localDateTimeCaptor=ArgumentCaptor.forClass(LocalDateTime.class);
        verify(product).setModifiedAt(localDateTimeCaptor.capture());
        assertTrue(time.isBefore(localDateTimeCaptor.getValue())||time.isEqual(localDateTimeCaptor.getValue()));
        assertEquals(expectedProductDto, actualProductDto);

    }

    @Test
    public void deleteProduct_WhenRequestValid_Expect_ReturnResponseProduct() {
        when(productRepository.findById(1L)).thenReturn(productOptional);
        ResponseProductDto expectedProduct = mock(ResponseProductDto.class);
        when(modelMapper.map(product, ResponseProductDto.class)).thenReturn(expectedProduct);
        ResponseProductDto actualProduct = productServiceImpl.deleteProduct(1L);
        verify(product).setStatus(ProductStatus.OUT_OF_STOCK);
        assertThat(actualProduct).isEqualTo(expectedProduct);

    }
}
