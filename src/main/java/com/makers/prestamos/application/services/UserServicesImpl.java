package com.makers.prestamos.application.services;

import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.UserServices;
import com.makers.prestamos.domain.ports.in.UserUseCases;

public class UserServicesImpl implements UserServices {
    private final UserUseCases userUseCases;

    public UserServicesImpl(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @Override
    public User getUserById(Long id) {
        return userUseCases.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userUseCases.getUserByEmail(email);
    }

    @Override
    public void validateIfUserAlreadyExists(String email, Long id) {
        userUseCases.validateIfUserAlreadyExists(email, id);
    }
}
