package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entities.Account;
import com.bank.entities.AccountType;
import com.bank.exception.AccountException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.UserNotFoundException;
import com.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountControlller {
    
    @Autowired 
    private AccountService accountService;

    @PostMapping("/add/{empId}/{userId}")
    public ResponseEntity<Account> addAccountToUserHandler(@PathVariable("empId") Integer empId, @PathVariable("userId") Long userId) throws EmployeeException, UserNotFoundException, BranchException, AccountException {
         Account acc = accountService.addAccountToUser(empId, userId);
        return new ResponseEntity<Account>(acc, HttpStatus.CREATED);
    }

    @GetMapping("/all_accounts")
    public ResponseEntity<List<Account>> getAllAccountsHanldler(){
        List<Account> list = accountService.getAllAccounts();
        return new ResponseEntity<List<Account>>(list, HttpStatus.OK);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAllAccountsByUserIdHanldler(@PathVariable("userId") Long userId) throws UserNotFoundException{
        List<Account> list = accountService.getAllAccountByUserId(userId);
        return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> getAccountByIdHanldler(@PathVariable("accountId") Integer accountId) throws UserNotFoundException, AccountException{
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @PutMapping("/transfer/{accountNumberFrom}/{accountNumberTo}/{amount}")
    public ResponseEntity<String> transferAmountHandler(@PathVariable("accountNumberFrom") String accountNumberFrom, @PathVariable("accountNumberTo") String accountNumberTo, @PathVariable("amount") Integer amount) throws AccountException, InsufficientBalance{
           String message = accountService.transferAmount(accountNumberFrom, accountNumberTo, amount);
           return new ResponseEntity<String>(message, HttpStatus.OK);

    }
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public ResponseEntity<String> withdrawAmountHandler(@PathVariable("accountNumber") String accountNumberFrom, @PathVariable("amount") Integer amount) throws AccountException, InsufficientBalance{
           String message = accountService.withDrawAmount(accountNumberFrom, amount);
           return new ResponseEntity<String>(message, HttpStatus.OK);

    }

    @PutMapping("/deposite/{accountNumber}/{amount}")
    public ResponseEntity<String> depositeAmountHandler(@PathVariable("accountNumber") String accountNumberFrom, @PathVariable("amount") Integer amount) throws AccountException{
           String message = accountService.depositeAmount(accountNumberFrom, amount);
           return new ResponseEntity<String>(message, HttpStatus.OK);

    }

    
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccountHandler(@PathVariable("accountNumber") String accountNumberFrom) throws AccountException{
           String message = accountService.deleteAccount(accountNumberFrom);
           return new ResponseEntity<String>(message, HttpStatus.OK);

    }
}
