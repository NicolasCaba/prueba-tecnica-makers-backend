package com.makers.prestamos.application.usecases;

import com.makers.prestamos.application.constants.Constants;
import com.makers.prestamos.application.enums.ExceptionCodes;
import com.makers.prestamos.application.exceptions.CreateException;
import com.makers.prestamos.application.exceptions.NotFoundException;
import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.models.enums.LoanStatus;
import com.makers.prestamos.domain.ports.in.LoanUseCases;
import com.makers.prestamos.domain.ports.out.LoanRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LoanUseCasesImpl implements LoanUseCases {
    private final LoanRepositoryPort loanRepositoryPort;

    public LoanUseCasesImpl(LoanRepositoryPort loanRepositoryPort) {
        this.loanRepositoryPort = loanRepositoryPort;
    }

    @Override
    public Loan create(Loan loan) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        Loan loanCreated;
        try {
            loanCreated = loanRepositoryPort.create(loan);
        } catch (Exception e) {
            throw new CreateException(ExceptionCodes.ERROR_CREATE.getCode(), ExceptionCodes.ERROR_CREATE.getMessage(), e);
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return loanCreated;
    }

    @Override
    public List<Loan> getAll() {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Loan> loan = loanRepositoryPort.getAll();
        if (loan.isEmpty()) {
            throw new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage());
        }
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return loan;
    }

    @Override
    public Loan getById(Long id) {
        log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
        Loan loan = loanRepositoryPort.getById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionCodes.ERROR_NOT_FOUND.getCode(), ExceptionCodes.ERROR_NOT_FOUND.getMessage()));
        log.info(Constants.LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName());
        return loan;
    }
}
