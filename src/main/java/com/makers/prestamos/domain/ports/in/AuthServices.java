package com.makers.prestamos.domain.ports.in;

import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.dto.LoginDto;

public interface AuthServices {
    User register(User user);

    LoginDto login(String email, String password);
}
