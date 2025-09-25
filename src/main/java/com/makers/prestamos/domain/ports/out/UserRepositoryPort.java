package com.makers.prestamos.domain.ports.out;

import com.makers.prestamos.domain.models.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    User createUser(User user);
}
