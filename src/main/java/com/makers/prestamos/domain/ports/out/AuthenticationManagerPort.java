package com.makers.prestamos.domain.ports.out;

public interface AuthenticationManagerPort {
    boolean authenticate(String email, String password);
}
