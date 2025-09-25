package com.makers.prestamos.infrastructure.repositories;

import com.makers.prestamos.infrastructure.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLoanRepository extends JpaRepository<LoanEntity, Long> {
}
