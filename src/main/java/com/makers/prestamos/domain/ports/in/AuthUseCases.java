package com.makers.prestamos.domain.ports.in;

import com.makers.prestamos.domain.models.User;

public interface AuthUseCases {
    User register(User user);

    User login(String email, String password);

    String generateToken(User user);
}
