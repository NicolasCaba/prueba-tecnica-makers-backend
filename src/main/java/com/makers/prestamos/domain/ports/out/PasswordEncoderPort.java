package com.makers.prestamos.domain.ports.out;

public interface PasswordEncoderPort {
    String getEncodedPassword(String password);
}
