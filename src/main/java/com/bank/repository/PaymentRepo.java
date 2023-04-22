package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{
    
}
