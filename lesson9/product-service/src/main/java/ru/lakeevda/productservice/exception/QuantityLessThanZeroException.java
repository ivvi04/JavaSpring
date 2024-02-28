package ru.lakeevda.productservice.exception;

public class QuantityLessThanZeroException extends RuntimeException {
    public QuantityLessThanZeroException(String message) {
        super(message);
    }
}
