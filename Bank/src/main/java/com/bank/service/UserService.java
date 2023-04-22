package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.bank.dto.UserDTO;
import com.bank.entities.User;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;

public interface UserService {
 
    public UserDTO addUser(UserDTO userDTO, Integer branchId) throws BranchException;

    public UserDTO updateUser(UserDTO userDTO, Long userId, String password) throws UserNotFoundException, PasswordException;

    public List<User> getAllUser();

    public List<User> getUserByBank(Integer bankId) throws BankException;

    public List<User> getUserByBranch(Integer branchId) throws BranchException;
    
    public User getUserById(Long userId) throws UserNotFoundException;

    public String deleteUserById(Long userId, String password) throws UserNotFoundException, PasswordException;

     // public String transferAmountToAccount(Long )

    // public 

    
}
