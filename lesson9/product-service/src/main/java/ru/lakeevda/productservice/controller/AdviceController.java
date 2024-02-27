package ru.lakeevda.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.productservice.exception.ExcessAmountException;
import ru.lakeevda.productservice.exception.ResourceNotFoundException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ExcessAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String excessAmount(ExcessAmountException e){
        return e.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resourceNotFound(ResourceNotFoundException e){
        return e.getMessage();
    }
}
