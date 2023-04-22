package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.Account;
import com.bank.entities.AccountType;
import com.bank.entities.Branch;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.bank.exception.AccountException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.AccountRepo;
import com.bank.repository.BranchRepo;
import com.bank.repository.EmployeeRepo;
import com.bank.repository.UserRepo;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo empRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public Account addAccountToUser(Integer empId, Long userId) throws EmployeeException, UserNotFoundException, BranchException, AccountException {
       Employee employee = empRepo.findById(empId).orElseThrow(()-> new EmployeeException("Employee not found with employeeId : "+ empId));
       User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with User Id : "+ userId));
       AccountType type = user.getAccountType();
       if(user.getAccounts() != null){
         for(Account acc : user.getAccounts()){
             if(acc.getAccountType() == type){
                throw new AccountException(" User already have same account type .. : "+ type);
             }
         }
       }

        Integer userBranchId = user.getBranch().getBranchId();
        Integer empBranch2Id= employee.getBranch().getBranchId();
        if(userBranchId != empBranch2Id ){
            throw new BranchException(" User and Employee branch not matched....Employee and User should be of same branch ..");
        }
       Account account = new Account();
       String accNo = generateAccountNumber();
       account.setUser(user);
       account.setAccountNumber(accNo);
       account.setAccountType(type);
       account.setBalance(user.getDepositeAmount());
       account.setAccountOpen(LocalDateTime.now());
       account.setEmployee(employee);
       account.setLoans(null);

       accountRepo.save(account);

       employee.getAccounts().add(account);
       user.getAccounts().add(account);

       empRepo.save(employee);
       userRepo.save(user);
    //    Branch branch =  employee.getBranch();
    //    branch.getPendingUserAccounts().remove(user.getUserId());
    //    branchRepo.save(branch);
       return account;

    }

    @Override
    public List<Account> getAllAccounts() {
       return accountRepo.findAll();
    }

    @Override
    public List<Account> getAllAccountByUserId(Long userId) throws UserNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with User Id : "+ userId));
        return user.getAccounts();
    }

    @Override
    public Account getAccountById(Integer accId) throws AccountException {
        Account account = accountRepo.findById(accId).orElseThrow(()-> new AccountException("Account not found with account Id : "+ accId));
        return account;
    }

    @Override
    public Account updateAccount(Integer accId, Account acc) throws AccountException {
        Account account = accountRepo.findById(accId).orElseThrow(()-> new AccountException("Account not found with account Id : "+ accId));
        if(acc.getAccountType() != null){
            account.setAccountType(acc.getAccountType());
        }
        if(acc.getBalance()>0){
            account.setBalance(acc.getBalance());
        }
        if(acc.getEmployee() != null){
            account.setEmployee(acc.getEmployee());
        }
         accountRepo.save(account);
        return account;
    }

    @Override
    public String transferAmount(String fromAccount, String toAccount, double amount)throws AccountException, InsufficientBalance {
        Account accountFrom = accountRepo.findByAccountNumber(fromAccount);
        if(accountFrom == null) 
          throw new AccountException("Account not found with account Id : "+ fromAccount);
        Account accountTo = accountRepo.findByAccountNumber(toAccount);
        if(accountTo == null) 
          throw new AccountException("Account not found with account Id : "+ toAccount);

          double balance = accountFrom.getBalance();
        if( balance >= amount){
            accountFrom.setBalance(balance - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
        }else {
            throw new InsufficientBalance("Insufficient Balance");
        }

        accountRepo.save(accountFrom);
        accountRepo.save(accountTo);
         
          return "transaction successful..!!";
         
    }

    @Override
    public String withDrawAmount(String accNo, double amount) throws AccountException, InsufficientBalance {
        Account account = accountRepo.findByAccountNumber(accNo);
        if(account == null) 
           throw new AccountException("Account not found with account Id : "+ accNo);

        if(account.getBalance() < amount){
            throw new InsufficientBalance("Insufficient Balance");
        }
        account.setBalance(account.getBalance() -  amount);
        accountRepo.save(account);
           return "Transaction successful..!";
      
    }

    @Override
    public String depositeAmount(String accNo, double amount) throws AccountException {
        Account account = accountRepo.findByAccountNumber(accNo);
        if(account == null) 
           throw new AccountException("Account not found with account Id : "+ accNo);

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);
           return "Transaction successful..!";
      
    }

    @Override
    public String deleteAccount(String accountNumber) throws AccountException {
        Account account = accountRepo.findByAccountNumber(accountNumber);
        accountRepo.delete(account);
        return "Account deleted successfully !";

    }
    

    /*
     * To generate account number automatically
     */
    private String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
