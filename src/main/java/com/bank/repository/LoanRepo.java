package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer> {
    
}
