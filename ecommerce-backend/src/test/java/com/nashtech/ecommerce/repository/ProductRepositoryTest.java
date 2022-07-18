package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.Category;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.enums.ProductStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Category category = new Category();
        category.setId(1L);
        categoryRepository.save(category);
        Category category2 = new Category();
        category2.setId(2L);
        categoryRepository.save(category2);
        Product product1 = Product.builder()
                .id(1L)
                .productName("Gsock")
                .price(1000L)
                .status(ProductStatus.STOCKING)
                .inventory(10)
                .category(category)
                .build();
        productRepository.save(product1);
        Product product2 = Product.builder()
                .id(2L)
                .productName("tissot")
                .price(1000L)
                .status(ProductStatus.OUT_OF_STOCK)
                .inventory(10)
                .category(category2)
                .build();
        productRepository.save(product2);
    }


    @Test
    void findProductByCategory_WhenRequestValid_Expect_ReturnProductPage() {
        int expected = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productRepository.findProductByCategory(1L, pageable);
        Assertions.assertThat(productPage.getContent().size()).isEqualTo(expected);
    }
}
