package com.makers.prestamos.infrastructure.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestLoginDto {
    @NotBlank(message = "Have to be not blank")
    @NotEmpty(message = "Have to be not empty")
    @NotNull(message = "Have to be not null")
    @Email(message = "Have to be a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;

}
