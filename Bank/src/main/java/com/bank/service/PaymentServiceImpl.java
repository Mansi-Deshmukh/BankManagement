package com.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.Account;
import com.bank.entities.Loan;
import com.bank.entities.Payment;
import com.bank.entities.User;
import com.bank.exception.AccountException;
import com.bank.exception.LoanException;
import com.bank.exception.PaymentException;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.AccountRepo;
import com.bank.repository.LoanRepo;
import com.bank.repository.PaymentRepo;
import com.bank.repository.UserRepo;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Payment repayLoan(double amount, Integer loanId) throws LoanException {
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new LoanException("loan not found with id : "+ loanId));
        if(loan.getStatus().equals("COMPLETED")){
            Account account = loan.getAccount();
            account.setBalance(account.getBalance() + amount);
            accountRepo.save(account);
            throw new LoanException("Loan is repaid, amount added to account .");
        }
         double loanamount = loan.getAmount();
         
         Payment payment = new Payment();
         payment.setLoan(loan);
         payment.setAmount(amount);
         payment.setPaymentDate(LocalDate.now());
         
         
            
        if(loanamount < amount){
            double remainingAmount = amount - loanamount;
          
            Account account = loan.getAccount();
            account.setBalance(account.getBalance() + remainingAmount);
            accountRepo.save(account);

            loan.setAmount(0);


            loan.setStatus("COMPLETED");
         }else {
            double remainingLoan = loanamount - amount;
            loan.setAmount(remainingLoan);
         }
         
         loan.getPayments().add(payment);
         
         

         paymentRepo.save(payment);
         loanRepo.save(loan);
        
            
        return payment;
    }

    @Override
    public Payment getPaymentById(Integer paymentId) throws PaymentException {
       Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new PaymentException("Payment id invalid "+ paymentId));
       return payment;
    }

    @Override
    public List<Payment> getAllPaymentByUser(Long userId) throws PaymentException, UserNotFoundException {
      User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found "));    
      List<Account> accList= user.getAccounts();
      if(accList.isEmpty()){
        throw new PaymentException("Account does not exist for user .");
      }
     List<Payment> list = new ArrayList<>();
     for(Account acc : accList ){
      
      for(Loan l : acc.getLoans()){
            list.add((Payment)  l.getPayments());
      }
     }
      return list;
    }
 
    @Override
    public List<Payment> getAllPaymentByAccount(String accountNo) throws AccountException {
      Account account = accountRepo.findByAccountNumber(accountNo);
      if(account == null){
        throw new AccountException("Account not found .");
      }
      List<Payment> list = new ArrayList<>();
      for(Loan l : account.getLoans()){
        list.add((Payment) l.getPayments());
      }
      return list;
    }

    @Override
    public String deletePayment(Integer paymentId) throws PaymentException {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow(() -> new PaymentException("Payment not found with id : "+ paymentId));
        Loan loan = payment.getLoan();
        loan.setAmount(loan.getAmount() + payment.getAmount());
        loanRepo.save(loan);

        paymentRepo.delete(payment);
        return "Payment Deleted/Reversed .";
    }


    
}
