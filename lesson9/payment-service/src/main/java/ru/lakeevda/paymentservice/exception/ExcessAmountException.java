package ru.lakeevda.paymentservice.exception;

public class ExcessAmountException extends RuntimeException {
    public ExcessAmountException(String message) {
        super(message);
    }
}
