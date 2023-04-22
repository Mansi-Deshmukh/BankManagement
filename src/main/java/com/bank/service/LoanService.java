package com.bank.service;

import com.bank.exception.AccountException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.LoanException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;
import com.bank.entities.Loan;

import java.util.List;

public interface LoanService {
    
    public Loan applyForLoan(String accountNo, double amount, double duration, String password) throws UserNotFoundException, AccountException, LoanException;

    public String approveLoan(Integer empId, Integer loanId, String password) throws EmployeeException, LoanException, InsufficientBalance;

    public Loan updateLoan(Integer loanId , String accountNo, double amount, double duration, String password) throws LoanException, AccountException;

    public String deleteLoan(Integer loanId, String password) throws LoanException, PasswordException;

    public List<Loan> getAllLoanByUser(Long userId) throws UserNotFoundException;

    public List<Loan> getAllLoanByAccount(String accountNumber) throws AccountException;

    public Loan getLoanById(Integer loanId) throws LoanException;
}
