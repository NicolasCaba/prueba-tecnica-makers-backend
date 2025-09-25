package com.makers.prestamos.application.services;

import com.makers.prestamos.domain.models.Role;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.in.AuthServices;
import com.makers.prestamos.domain.ports.in.AuthUseCases;
import com.makers.prestamos.domain.ports.in.RoleUseCases;
import com.makers.prestamos.domain.ports.in.dto.LoginDto;

import java.util.Set;

public class AuthServicesImpl implements AuthServices {
    private final AuthUseCases authUseCases;
    private final RoleUseCases roleUseCases;

    public AuthServicesImpl(AuthUseCases authUseCases, RoleUseCases roleUseCases) {
        this.authUseCases = authUseCases;
        this.roleUseCases = roleUseCases;
    }

    @Override
    public User register(User user) {
        Role role = roleUseCases.getRoleByName("USER");
        user.setRoles(Set.of(role));
        return authUseCases.register(user);
    }

    @Override
    public LoginDto login(String email, String password) {
        User user = authUseCases.login(email, password);
        String token = authUseCases.generateToken(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setUser(user);
        loginDto.setToken(token);
        return loginDto;
    }
}
