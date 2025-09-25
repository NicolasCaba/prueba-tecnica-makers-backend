package com.makers.prestamos.domain.ports.in.dto;

import com.makers.prestamos.domain.models.User;
import lombok.Data;

@Data
public class LoginDto {
    private User user;
    private String token;
}
