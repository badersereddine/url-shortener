package com.example.shortener.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        Map<String,String> errors = new HashMap<>();
        for (var e : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) e).getField();
            errors.put(field, e.getDefaultMessage());
        }
        pd.setTitle("Validation failed");
        pd.setProperty("errors", errors);
        return pd;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleRSE(ResponseStatusException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(ex.getStatusCode());
        pd.setTitle(ex.getReason());
        return pd;
    }
}
