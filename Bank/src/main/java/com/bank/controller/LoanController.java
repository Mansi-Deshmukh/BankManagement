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

import com.bank.entities.Loan;
import com.bank.exception.AccountException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.LoanException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;
import com.bank.service.LoanService;

// import io.swagger.models.Response;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/add/{accountNo}/{amount}/{duration}/{password}")
    public ResponseEntity<Loan> applyForLoanHandler(@PathVariable("accountNo") String accountNo, @PathVariable("amount") double amount ,@PathVariable("duration") double duration ,@PathVariable("password") String password) throws LoanException, UserNotFoundException, AccountException{
        Loan loan = loanService.applyForLoan(accountNo, amount, duration, password);
        return new ResponseEntity<Loan>(loan, HttpStatus.CREATED);
    }

    @PutMapping("/approve/{empId}/{loanId}/{password}")
    public ResponseEntity<String> approveLoanHandler(@PathVariable("empId") Integer empId, @PathVariable("loanId") Integer loanId, @PathVariable("password") String password) throws LoanException, EmployeeException, InsufficientBalance{
        String msg = loanService.approveLoan(empId, loanId, password);
        return new ResponseEntity<String>(msg, HttpStatus.OK);

    }

    @GetMapping("/get_loan/{loanId}")
    public ResponseEntity<Loan> getLoanByIdHandler(@PathVariable("loanId") Integer loanId){
        Loan loan = loanService.getLoanById(loanId);
        return new ResponseEntity<Loan>(loan, HttpStatus.OK);
    }

    @DeleteMapping("/delete_loan/{loanId}/{password}")
    public ResponseEntity<String> deleteLoanHandler(@PathVariable("loanId") Integer loanId, @PathVariable("password") String password) throws LoanException, PasswordException{
       String msg = loanService.deleteLoan(loanId, password);
        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/get_loan_userId/{userId}")
    public ResponseEntity<List<Loan>> getLoanByUserHandler(@PathVariable("loanId") Long loanId) throws UserNotFoundException{
        List<Loan> loan = loanService.getAllLoanByUser(loanId);
        return new ResponseEntity<List<Loan>>(loan, HttpStatus.OK);

    }
    
}
