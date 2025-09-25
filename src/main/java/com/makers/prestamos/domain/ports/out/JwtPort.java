package com.makers.prestamos.domain.ports.out;

import com.makers.prestamos.domain.models.User;

public interface JwtPort {
    String generateToken(User user);
}
