package com.makers.prestamos.application.enums;

import lombok.Getter;

@Getter
public enum ExceptionCodes {
    ERROR_NOT_FOUND("not_found", "Resource not found"),
    ERROR_EMPTY_RESOURCE("empty_resource", "Empty resource"),
    ERROR_CREATE("create_error", "Error creating entity"),
    ERROR_USER_ALREADY_EXISTS("user_already_exists", "User already exists"),
    ERROR_UNAUTHORIZED("unathorized", "unathorized");

    private final String code;
    private final String message;

    ExceptionCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

