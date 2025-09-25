package com.makers.prestamos.domain.models;

import com.makers.prestamos.domain.models.enums.LoanStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private Long id;
    private BigDecimal amount;
    private LoanStatus status;
}
