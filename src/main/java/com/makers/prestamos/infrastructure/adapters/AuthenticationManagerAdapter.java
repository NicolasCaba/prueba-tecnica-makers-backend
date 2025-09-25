package com.makers.prestamos.infrastructure.adapters;

import com.makers.prestamos.domain.ports.out.AuthenticationManagerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerAdapter implements AuthenticationManagerPort {
    private final AuthenticationManager authenticationManager;

    public AuthenticationManagerAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public boolean authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        return authentication.isAuthenticated();
    }
}
