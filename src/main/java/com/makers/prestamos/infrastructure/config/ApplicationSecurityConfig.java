package com.makers.prestamos.infrastructure.config;

import com.makers.prestamos.application.enums.ExceptionCodes;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.infrastructure.entities.UserEntity;
import com.makers.prestamos.infrastructure.repositories.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationSecurityConfig {
    private final JpaUserRepository jpaUserRepository;

    public ApplicationSecurityConfig(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity user = jpaUserRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
