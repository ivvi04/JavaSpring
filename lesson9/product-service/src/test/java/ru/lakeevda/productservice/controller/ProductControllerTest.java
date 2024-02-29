package ru.lakeevda.productservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product = new Product();
        product.setId(1L);
        product.setName("Продукт");
        product.setAmount(1000);
        product.setReserved(0);
    }

    @Test
    void getProducts() throws Exception {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getProduct() throws Exception {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void bayProduct() throws Exception {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        mockMvc.perform(post("/{id}", product.getId()).param("amount", "100"))
                .andExpect(status().isOk());

        assertEquals(product.getAmount(), 900);
        assertEquals(product.getReserved(), -100);
    }

    @Test
    void reservedProduct() throws Exception {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        mockMvc.perform(post("/{id}/reserved", product.getId()).param("amount", "100"))
                .andExpect(status().isOk());

        assertEquals(product.getReserved(), 100);
    }

    @Test
    void reservedProductRollback() throws Exception {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        mockMvc.perform(post("/{id}/reserved/rollback", product.getId()).param("amount", "100"))
                .andExpect(status().isOk());

        assertEquals(product.getReserved(), -100);
    }
}