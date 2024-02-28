package ru.lakeevda.shopclient.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String name;
    private int amount;
    private BigDecimal price;
    private byte[] image;
    private String imageString;
}
