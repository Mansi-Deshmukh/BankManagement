package com.bank.service;

import java.util.List;

import com.bank.entities.Account;
import com.bank.entities.AccountType;
import com.bank.exception.AccountException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.UserNotFoundException;

public interface AccountService {
    
    public Account addAccountToUser(Integer empId ,Long userId) throws EmployeeException, UserNotFoundException, BranchException, AccountException;

    public List<Account> getAllAccounts();

    public List<Account> getAllAccountByUserId(Long userId) throws UserNotFoundException;

    public Account getAccountById(Integer accId) throws AccountException;

    public Account updateAccount(Integer accId, Account account) throws AccountException;

    public String transferAmount(String fromAccount , String toAccount, double amount) throws AccountException, InsufficientBalance;

    public String withDrawAmount(String accNo, double amount) throws AccountException, InsufficientBalance; 

    public String depositeAmount(String accNo, double amount) throws AccountException;

    public String deleteAccount(String accountNumber) throws AccountException;
    
}
