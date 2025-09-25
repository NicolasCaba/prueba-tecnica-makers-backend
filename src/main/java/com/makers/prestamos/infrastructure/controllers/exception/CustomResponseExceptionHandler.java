package com.makers.prestamos.infrastructure.controllers.exception;


import com.makers.prestamos.application.exceptions.EmptyResourceException;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.application.exceptions.ResourceAlreadyExistsException;
import com.makers.prestamos.infrastructure.controllers.ApiResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomResponseExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ApiResponseEntity<Object> handleNotFoundExceptions(NotFoundException ex) {
        log.error("Not found exception: ", ex.getCause());
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("message", ex.getMessage());
        return ApiResponseEntity.response(map, "not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ApiResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.error("Resource already exists exception: ", ex.getCause());
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("message", ex.getMessage());
        return ApiResponseEntity.response(map, "resource already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResourceException.class)
    public ApiResponseEntity<Object> handleEmptyResourceException(EmptyResourceException ex) {
        log.error("Empty resource exception> ", ex.getCause());
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("message", ex.getMessage());
        return ApiResponseEntity.response(map, "empty resource", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ApiResponseEntity.response(errors, "validation errors", HttpStatus.BAD_REQUEST);
    }
}
