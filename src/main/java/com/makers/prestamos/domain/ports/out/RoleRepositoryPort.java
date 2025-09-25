package com.makers.prestamos.domain.ports.out;

import com.makers.prestamos.domain.models.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> getRoleByName(String name);
}
