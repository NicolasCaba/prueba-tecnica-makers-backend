package com.makers.prestamos.infrastructure.entities;

import com.makers.prestamos.domain.models.enums.LoanStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan", schema = "public")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private LoanStatus status;
}
