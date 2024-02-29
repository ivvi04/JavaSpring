package ru.lakeevda.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceModuleTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
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

        assertThat(product.getAmount()).isEqualTo(900);
        assertThat(product.getReserved()).isEqualTo(-100);
    }

    @Test
    void reservedProduct() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.reservedProduct(product.getId(), 100);

        assertThat(product.getReserved()).isEqualTo(100);
    }

    @Test
    void reservedProductRollback() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.reservedProductRollback(product.getId(), 100);

        assertThat(product.getReserved()).isEqualTo(-100);
    }
}