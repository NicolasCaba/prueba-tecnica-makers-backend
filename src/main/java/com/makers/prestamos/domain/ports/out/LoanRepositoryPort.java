package com.makers.prestamos.domain.ports.out;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.enums.LoanStatus;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    List<Loan> getAll();

    Loan create(Loan loan);

    Optional<Loan> getById(Long id);
}
