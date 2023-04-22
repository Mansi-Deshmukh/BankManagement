package com.bank.service;

import java.util.List;

import com.bank.dto.BranchDTO;
import com.bank.dto.UserDTO;
import com.bank.entities.Branch;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.UserNotFoundException;

public interface BranchService {
    
        // public BranchDTO addBranch(BranchDTO branch) throws BranchException;

       public BranchDTO addBranch(Integer bankId , BranchDTO branch) throws BranchException, BankException;
    //    public List<Branch> getAllBranches();
      //  public List<Branch> getAllBranchesByBank(Integer bankId) throws BankException, BranchException;
       public  Branch getBranchById(Integer branchId) throws BranchException;
       public BranchDTO updateBranch(BranchDTO branch, Integer branchId) throws BranchException , BankException;
       public String deleteBranchById(Integer branchId) throws BranchException;
       public List<User> getUsersByBranchId(Integer branchId) throws BranchException, UserNotFoundException;
       public List<Employee> getEmployeesByBranchId(Integer branchId) throws BranchException, EmployeeException;
    
       public Integer countBranches(Integer bankId) throws BankException;
       public List<String> getBrachNameByBank(Integer bankId) throws BankException;
    //    public List<UserDTO> getUserDTOByBranchId(Integer branchId) throws BranchException, UserNotFoundException;
    public List<BranchDTO> getBranchesByBank(Integer branchId) throws BankException, BranchException;

}
