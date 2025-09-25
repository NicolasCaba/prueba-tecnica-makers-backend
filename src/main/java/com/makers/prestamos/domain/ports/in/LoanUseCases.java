package com.makers.prestamos.domain.ports.in;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.enums.LoanStatus;

import java.util.List;

public interface LoanUseCases {
    Loan create(Loan loan);

    List<Loan> getAll();

    Loan getById(Long id);
}
