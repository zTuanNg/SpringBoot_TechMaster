package com.example.Day16.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, details);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
