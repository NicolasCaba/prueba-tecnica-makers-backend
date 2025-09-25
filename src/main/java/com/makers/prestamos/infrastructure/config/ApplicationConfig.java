package com.makers.prestamos.infrastructure.config;

import com.makers.prestamos.application.services.AuthServicesImpl;
import com.makers.prestamos.application.services.LoanServicesImpl;
import com.makers.prestamos.application.services.UserServicesImpl;
import com.makers.prestamos.application.usecases.AuthUseCasesImpl;
import com.makers.prestamos.application.usecases.LoanUseCasesImpl;
import com.makers.prestamos.application.usecases.RoleUserCasesImpl;
import com.makers.prestamos.application.usecases.UserUseCasesImpl;
import com.makers.prestamos.domain.ports.out.*;
import com.makers.prestamos.infrastructure.adapters.AuthenticationManagerAdapter;
import com.makers.prestamos.infrastructure.adapters.JwtAdapter;
import com.makers.prestamos.infrastructure.adapters.PasswordEncoderAdapter;
import com.makers.prestamos.infrastructure.repositories.JpaLoanRepositoryAdapter;
import com.makers.prestamos.infrastructure.repositories.JpaRoleRepositoryAdapter;
import com.makers.prestamos.infrastructure.repositories.JpaUserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserServicesImpl userServices(UserRepositoryPort userRepositoryPort) {
        return new UserServicesImpl(
                new UserUseCasesImpl(userRepositoryPort)
        );
    }

    @Bean
    public LoanServicesImpl loanServices(LoanRepositoryPort loanRepositoryPort) {
        return new LoanServicesImpl(
                new LoanUseCasesImpl(loanRepositoryPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
        return jpaUserRepositoryAdapter;
    }

    @Bean
    public RoleRepositoryPort roleRepositoryPort(JpaRoleRepositoryAdapter jpaRoleRepositoryAdapter) {
        return jpaRoleRepositoryAdapter;
    }

    @Bean
    public LoanRepositoryPort loanRepositoryPort(JpaLoanRepositoryAdapter jpaLoanRepositoryAdapter) {
        return jpaLoanRepositoryAdapter;
    }

    @Bean
    public AuthServicesImpl authServices(UserRepositoryPort userRepositoryPort,
                                         PasswordEncoderPort passwordEncoderPort,
                                         AuthenticationManagerPort authenticationManagerPort,
                                         JwtPort jwtPort,
                                         RoleRepositoryPort roleRepositoryPort) {
        return new AuthServicesImpl(
                new AuthUseCasesImpl(userRepositoryPort, passwordEncoderPort, authenticationManagerPort, jwtPort),
                new RoleUserCasesImpl(roleRepositoryPort)
        );
    }

    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoderAdapter passwordEncoderAdapter) {
        return passwordEncoderAdapter;
    }

    @Bean
    public AuthenticationManagerPort authenticationManagerPort(AuthenticationManagerAdapter authenticationManagerAdapter) {
        return authenticationManagerAdapter;
    }

    @Bean
    public JwtPort jwtPort(JwtAdapter jwtAdapter) {
        return jwtAdapter;
    }
}
