package com.makers.prestamos.infrastructure.dto;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.models.enums.LoanStatus;
import com.makers.prestamos.domain.models.enums.UserStatus;

import java.math.BigDecimal;

public class LoanDtoDomainModelMapper {
    private LoanDtoDomainModelMapper() {
        throw new IllegalStateException("UserDtoDomainModelMapper utility class");
    }

    public static Loan toDomainModel(BigDecimal amount, String status) {
        Loan loan = new Loan();
        loan.setAmount(amount);
        loan.setStatus(status != null ? LoanStatus.valueOf(status) : null);
        return loan;
    }
}
