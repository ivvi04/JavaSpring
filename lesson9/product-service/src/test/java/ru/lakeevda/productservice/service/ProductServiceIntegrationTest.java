package ru.lakeevda.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceIntegrationTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Продукт");
        product.setAmount(1000);
        product.setReserved(0);
    }

    @Test
    void bayProduct() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.bayProduct(product.getId(), 100);

        assertEquals(product.getAmount(), 900);
        assertEquals(product.getReserved(), -100);
    }

    @Test
    void reservedProduct() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.reservedProduct(product.getId(), 100);

        assertEquals(product.getReserved(), 100);
    }

    @Test
    void reservedProductRollback() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.reservedProductRollback(product.getId(), 100);

        assertEquals(product.getReserved(), -100);
    }
}