package com.bank.service;

import java.util.List;

import com.bank.entities.Payment;
import com.bank.exception.AccountException;
import com.bank.exception.LoanException;
import com.bank.exception.PaymentException;
import com.bank.exception.UserNotFoundException;

public interface PaymentService {
    
    public Payment repayLoan(double amount, Integer loanId) throws LoanException;

    public Payment getPaymentById(Integer paymentId) throws PaymentException;

    public List<Payment> getAllPaymentByUser(Long userId) throws PaymentException, UserNotFoundException;

    public List<Payment> getAllPaymentByAccount(String accountNo) throws AccountException;

    public String deletePayment(Integer paymentId) throws PaymentException;
}
