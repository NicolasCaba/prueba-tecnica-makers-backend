package com.makers.prestamos.infrastructure.repositories;

import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.User;
import com.makers.prestamos.domain.models.enums.LoanStatus;
import com.makers.prestamos.domain.ports.out.LoanRepositoryPort;
import com.makers.prestamos.infrastructure.entities.LoanEntity;
import com.makers.prestamos.infrastructure.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaLoanRepositoryAdapter implements LoanRepositoryPort {
    private final JpaLoanRepository jpaLoanRepository;
    private final ModelMapper modelMapper;

    public JpaLoanRepositoryAdapter(JpaLoanRepository jpaLoanRepository) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<Loan> getAll() {
        return jpaLoanRepository.findAll()
                .stream()
                .map(loan -> modelMapper.map(loan, Loan.class))
                .collect(Collectors.toList());
    }

    @Override
    public Loan create(Loan loan) {
        LoanEntity loanEntity = modelMapper.map(loan, LoanEntity.class);
        LoanEntity loanCreated = jpaLoanRepository.save(loanEntity);
        return modelMapper.map(loanCreated, Loan.class);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        Optional<LoanEntity> loanEntity = jpaLoanRepository.findById(id);
        return loanEntity.map(loan -> modelMapper.map(loan, Loan.class));
    }
}
