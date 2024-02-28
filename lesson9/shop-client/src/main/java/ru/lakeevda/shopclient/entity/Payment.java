package ru.lakeevda.shopclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Payment {
    private Long creditNumber;
    private Long debitNumber;
    private BigDecimal sum;
}
