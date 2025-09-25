package com.makers.prestamos.infrastructure.adapters;

import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.ports.out.JwtPort;
import com.makers.prestamos.infrastructure.config.Jwt;
import com.makers.prestamos.infrastructure.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JwtAdapter implements JwtPort {
    private final Jwt jwtService;
    private final ModelMapper modelMapper;

    public JwtAdapter(Jwt jwtService) {
        this.jwtService = jwtService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(modelMapper.map(user, UserEntity.class));
    }
}
