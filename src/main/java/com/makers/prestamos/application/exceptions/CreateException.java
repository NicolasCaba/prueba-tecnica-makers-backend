package com.makers.prestamos.application.exceptions;

import lombok.Getter;

import java.io.Serial;

public class CreateException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final String code;

    private final String message;

    public CreateException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public CreateException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
