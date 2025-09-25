package com.makers.prestamos.application.exceptions;

import java.io.Serial;

public class ResourceAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;

    public ResourceAlreadyExistsException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public ResourceAlreadyExistsException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
