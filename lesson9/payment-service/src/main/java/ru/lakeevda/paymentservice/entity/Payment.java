package ru.lakeevda.paymentservice.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    private Long creditNumber;
    private Long debitNumber;
    private BigDecimal sum;

}
