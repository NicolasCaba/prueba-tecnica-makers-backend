package com.makers.prestamos.infrastructure.repositories;

import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.out.UserRepositoryPort;
import com.makers.prestamos.infrastructure.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    private final JpaUserRepository jpaUserRepository;
    private final ModelMapper modelMapper;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);
        return userEntity.map(userEntityToMap -> modelMapper.map(userEntityToMap, User.class));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<UserEntity> userEntity = jpaUserRepository.findByEmail(email);
        return userEntity.map(userEntityToMap -> modelMapper.map(userEntityToMap, User.class));
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity userCreated = jpaUserRepository.save(userEntity);
        return modelMapper.map(userCreated, User.class);
    }
}
