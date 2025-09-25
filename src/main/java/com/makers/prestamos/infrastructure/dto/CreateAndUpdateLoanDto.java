package com.makers.prestamos.infrastructure.dto;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAndUpdateLoanDto {
    @NotBlank(message = "Have to be not blank")
    @NotEmpty(message = "Have to be not empty")
    @NotNull(message = "Have to be not null")
    private BigDecimal amount;

    @NotBlank(message = "Have to be not blank")
    @NotEmpty(message = "Have to be not empty")
    @NotNull(message = "Have to be not null")
    private String status;

    public static Loan toDomainModel(CreateAndUpdateLoanDto createAndUpdateLoanDto) {
        return LoanDtoDomainModelMapper.toDomainModel(
                createAndUpdateLoanDto.getAmount(),
                createAndUpdateLoanDto.getStatus()
        );
    }
}
