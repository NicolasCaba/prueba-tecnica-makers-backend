package com.makers.prestamos.domain.ports.in;

import com.makers.prestamos.domain.models.Role;

public interface RoleUseCases {
    Role getRoleByName(String name);
}
