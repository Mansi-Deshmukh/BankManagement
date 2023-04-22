package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{
    
    public Account findByAccountNumber(String accountNumber);
}
