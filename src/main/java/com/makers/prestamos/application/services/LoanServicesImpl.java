package com.makers.prestamos.application.services;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.enums.LoanStatus;
import com.makers.prestamos.domain.ports.in.LoanServices;
import com.makers.prestamos.domain.ports.in.LoanUseCases;
import jakarta.transaction.Transactional;

import java.util.List;

public class LoanServicesImpl implements LoanServices {
    private final LoanUseCases loanUseCases;

    public LoanServicesImpl(LoanUseCases loanUseCases) {
        this.loanUseCases = loanUseCases;
    }

    @Override
    @Transactional
    public Loan create(Loan loan) {
        return loanUseCases.create(loan);
    }

    @Override
    public List<Loan> getAll() {
        return loanUseCases.getAll();
    }

    @Override
    public Loan setStatus(Long id, LoanStatus status) {
        Loan loan = loanUseCases.getById(id);
        loan.setStatus(status);
        return loanUseCases.create(loan);
    }
}
