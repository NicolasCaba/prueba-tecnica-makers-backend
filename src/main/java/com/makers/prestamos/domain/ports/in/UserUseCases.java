package com.makers.prestamos.domain.ports.in;

import com.makers.prestamos.domain.models.User;

public interface UserUseCases {
    User getUserById(Long id);

    User getUserByEmail(String email);

    void validateIfUserAlreadyExists(String email, Long id);
}
