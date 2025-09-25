package com.makers.prestamos.infrastructure.controllers.v1;

import com.makers.prestamos.application.services.LoanServicesImpl;
import com.makers.prestamos.domain.models.Loan;
import com.makers.prestamos.domain.models.enums.LoanStatus;
import com.makers.prestamos.infrastructure.controllers.ApiResponseEntity;
import com.makers.prestamos.infrastructure.dto.CreateAndUpdateLoanDto;
import com.makers.prestamos.infrastructure.dto.UpdateStatusLoanDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/loan")
public class LoanController {
    private final LoanServicesImpl loanServices;

    public LoanController(LoanServicesImpl loanServices) {
        this.loanServices = loanServices;
    }

    @PostMapping
    public ApiResponseEntity<Loan> creteLoan(@Valid @RequestBody CreateAndUpdateLoanDto createAndUpdateLoanDto) {
        Loan loanCreated = loanServices.create(CreateAndUpdateLoanDto.toDomainModel(createAndUpdateLoanDto));
        return ApiResponseEntity.response(loanCreated, "loan created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ApiResponseEntity<Loan> updateLoanStatus(@Valid @RequestBody UpdateStatusLoanDto updateStatusLoanDto, @PathVariable Long id) {
        Loan loanUpdated = loanServices.setStatus(id, LoanStatus.valueOf(updateStatusLoanDto.getStatus()));
        return ApiResponseEntity.response(loanUpdated, "loan updated", HttpStatus.OK);
    }

    @GetMapping
    public ApiResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanServices.getAll();
        return ApiResponseEntity.response(loans, "loans get", HttpStatus.OK);
    }
}
