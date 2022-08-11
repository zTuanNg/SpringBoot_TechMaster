package com.example.Day15_MidTerm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    // Handle Not Found
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus
    public ErrorMessage handleNotFoundException(NotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // Handle Bad Request
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus
    public ErrorMessage handleNotFoundException(BadRequestException e){
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Handle Other Exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public ErrorMessage handleNotFoundException(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
