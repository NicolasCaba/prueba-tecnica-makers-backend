package com.makers.prestamos.infrastructure.repositories;


import com.makers.prestamos.domain.models.Role;
import com.makers.prestamos.domain.ports.out.RoleRepositoryPort;
import com.makers.prestamos.infrastructure.entities.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class JpaRoleRepositoryAdapter implements RoleRepositoryPort {
    private final JpaRoleRepository jpaRoleRepository;
    private final ModelMapper modelMapper;

    public JpaRoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        Optional<RoleEntity> roleEntity = jpaRoleRepository.findRoleEntityByName(name);
        return roleEntity.map(roleToMap -> modelMapper.map(roleToMap, Role.class));
    }
}
