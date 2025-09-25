package com.makers.prestamos.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusLoanDto {
    @NotBlank(message = "Have to be not blank")
    @NotEmpty(message = "Have to be not empty")
    @NotNull(message = "Have to be not null")
    private String status;
}
