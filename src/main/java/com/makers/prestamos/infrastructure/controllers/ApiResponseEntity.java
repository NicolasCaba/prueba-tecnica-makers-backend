package com.makers.prestamos.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseEntity<T> extends ResponseEntity<ApiResponse<T>> {

    private ApiResponseEntity(ApiResponse<T> body, HttpStatus status) {
        super(body, status);
    }

    public static <T> ApiResponseEntity<T> response(T data, String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(message, status.value(), data);
        return new ApiResponseEntity<>(response, status);
    }

    public static <T> ApiResponseEntity<T> response(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(message, status.value());
        return new ApiResponseEntity<>(response, status);
    }
}