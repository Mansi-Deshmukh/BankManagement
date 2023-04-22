package com.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.Account;
import com.bank.entities.Employee;
import com.bank.entities.Gender;
import com.bank.entities.Loan;
import com.bank.entities.User;
import com.bank.exception.AccountException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.LoanException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.AccountRepo;
import com.bank.repository.EmployeeRepo;
import com.bank.repository.LoanRepo;
import com.bank.repository.UserRepo;

@Service
public class LoanServiceImpl implements  LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private EmployeeRepo empRepo;


    @Autowired
    private UserRepo userRepo;

    @Override
    public Loan applyForLoan(String accountNo, double amount, double duration,String password)throws UserNotFoundException, AccountException, LoanException {
        Account account = accountRepo.findByAccountNumber(accountNo);
        if(account == null){
            throw new AccountException("Invalid account number "+ accountNo);
        }
        if(!account.getLoans().isEmpty()){
            for(Loan l : account.getLoans()){
                if(l.getAmount() == amount){
                    throw new LoanException("Already applied for laon .");
                }
            }
        }
        User user = account.getUser();
        if(!user.getPassoword().equals(password)){
            throw new AccountException("Incorrect password ");
        }
        double balance = account.getBalance();
        if (balance < amount) {
           throw new LoanException("Insufficient funds in account for loan.");
        }

         double interestRate = calculateInterestRate(user);
         double monthlyPayment = calculateMonthlyPayment(amount, duration, interestRate);

         Loan loan = new Loan(); //(amount, duration, interestRate, monthlyPayment);
         loan.setAccount(account);
         loan.setAmount(amount);
         loan.setDuration(duration);
         loan.setInterestRate(interestRate);
         loan.setMonthlyPayment(monthlyPayment);
         loan.setStatus("PENDING");
         
         loanRepo.save(loan);
         account.getLoans().add(loan);
 
         
         accountRepo.save(account);
         return loan;
         
    }

    @Override
    public String approveLoan(Integer empId, Integer loanId, String password)     throws EmployeeException, LoanException, InsufficientBalance {
         Employee employee = empRepo.findById(empId).orElseThrow(() -> new EmployeeException("employee not found with id : "+empId));
         if(!employee.getPassoword().equals(password)){
            throw new EmployeeException("Incorrect password");
         }
         Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanException("loan not found with id : "+ loanId));
         User user = loan.getAccount().getUser();
         boolean isEligible = checkLoanEligibility(user);

         if (!isEligible) {
            loan.setStatus("REJECTED");
             throw new LoanException("User is not eligible for a loan.");
         } 
         loan.setStatus("APPROVED");
         Account acc = loan.getAccount();
         acc.getLoans().add(loan);
         acc.setBalance((int) loan.getAmount());

         loanRepo.save(loan);
         accountRepo.save(acc);

        return "loan approved !!";
    }

    @Override
    public Loan updateLoan(Integer loanId, String accountNo, double amount, double duration , String password) throws LoanException, AccountException {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanException("loan not found with id : "+ loanId));
        Account account = accountRepo.findByAccountNumber(accountNo);
        if(account == null){
            throw new AccountException("Invalid account number "+ accountNo);
        }
        User user = account.getUser();
        if(!user.getPassoword().equals(password)){
            throw new AccountException("Incorrect password ");
        }
        // Account acc = loan.getAccount();
        // if(!acc.getAccountNumber().equals(accountNo)){
        //     acc.getLoans().remove(loan);
        // }
        loan.setAccount(account);
        loan.setAmount(amount);
        loan.setDuration(duration);

        account.getLoans().add(loan);
        

        return loan;

    }

    @Override
    public String deleteLoan(Integer loanId, String password) throws LoanException, PasswordException {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanException("loan not found with id : "+ loanId));
        if(loan.getStatus().equals("PENDING")){
           User user = loan.getAccount().getUser();
           if(!user.getPassoword().equals(password)){
             throw new PasswordException("Invaliid password");
           }
            loanRepo.delete(loan);
           return "Loan Deleted..";

        }
        else throw new LoanException("Loan is approved/ completed cannot be deleted now..");
    }

    @Override
    public Loan getLoanById(Integer loanId) throws LoanException {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanException("loan not found with id : "+ loanId));
        return loan;      
    }





 
    //Check eligibility of user for loan 
    public boolean checkLoanEligibility(User user) {
        
        int age = user.getAge();
        
        
        double totalBalance = user.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
        
        if (age >= 18 && age <= 65 && totalBalance >= 10000) {
            return true; 
        } else {
            return false; 
        }
    }

    public double calculateInterestRate(User user) {
        int age = user.getAge();
        double interestRate = 4.4;
    
        if (age < 18 || age >= 60) {
            interestRate = 4.4;
        } else {
             String gender = user.getGender();
            if (gender.equalsIgnoreCase("female")) {
                interestRate = 2.2;
            } else {
                interestRate = 3.0;
            }
        }
    
        return interestRate;
    }
    
    public double calculateMonthlyPayment(double amount, double duration, double interestRate){
        
        double total = amount*interestRate/100;
        double pricipleAMount = amount + total;
       
        double totalInterest = pricipleAMount / duration;
        return totalInterest;
    }

    @Override
    public List<Loan> getAllLoanByUser(Long userId) throws UserNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() ->new UserNotFoundException("User not found with user id : "+ userId));
         List<Account> acclist = user.getAccounts();
         List<Loan> loanList = new ArrayList<>();
         for(Account a : acclist ){ 
            for(Loan loan : a.getLoans())
                loanList.add(loan);
         }
        return loanList;
    }

    @Override
    public List<Loan> getAllLoanByAccount(String accountNumber) throws AccountException {
        Account acc = accountRepo.findByAccountNumber(accountNumber);
        if(acc == null){
            throw new AccountException(accountNumber+ " account not found .");
        }
        return acc.getLoans();
    }

    
    
}
