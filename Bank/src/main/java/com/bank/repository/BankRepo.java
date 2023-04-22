package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer>{
    
    public Bank findByBankName(String bankName);
}
