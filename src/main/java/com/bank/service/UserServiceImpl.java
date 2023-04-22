package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.UniqueConstraintHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.UserDTO;
import com.bank.entities.Branch;
import com.bank.entities.User;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.BranchRepo;
import com.bank.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public UserDTO addUser(UserDTO userDTO, Integer branchId) throws BranchException {
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
        User user = dtoToUser(userDTO);
        
       List<User> userList = branch.getUsers();
       for(User u : userList){
        if(u.getAdhaarNumber() == user.getAdhaarNumber()){
            throw new BranchException("User already registered with adhaar number : "+ user.getAdhaarNumber());
        }
       }
        user.setBranch(branch);
        branch.getUsers().add(user);
        userRepo.save(user);
        // branch.getPendingUserAccounts().add(user.getUserId());
        branchRepo.save(branch);

        return userToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId, String password) throws UserNotFoundException , PasswordException{
       Optional<User> existuser = userRepo.findById(userId); //.orElseThrow(()-> new UserNotFoundException("User not found with the adhaar number "+ adharNo));
       if(existuser.isEmpty()){
        throw new UserNotFoundException("User not found with the userId  "+ userId);
       }
       User user = existuser.get();
       if(!user.getPassoword().equals(password)){
        throw new PasswordException("Invalid password");
       }
       User updateUser = dtoToUser(userDTO); 
      
    //    if(updateUser.getAccountType() != null){
    //     user.setAccountType(updateUser.getAccountType());
    //    }
       if(updateUser.getAge() != null){
        user.setAge(updateUser.getAge());
       }
       if(updateUser.getDepositeAmount() > 0.00){
        user.setDepositeAmount(updateUser.getDepositeAmount());
       }
       if(updateUser.getEmail() != null){
        user.setEmail(updateUser.getEmail());
       }
       if(updateUser.getFirstName() != null){
        user.setFirstName(updateUser.getFirstName());
       }
       if(updateUser.getLastName() != null){
        user.setLastName(updateUser.getLastName());
       }
       if(updateUser.getPanCardNumber() != null){
        user.setPanCardNumber(updateUser.getPanCardNumber());
       }
       if(updateUser.getGender() != null){
        user.setGender(updateUser.getGender());
       }
       if(updateUser.getPhone() != null){
        user.setPhone(updateUser.getPhone());
       }
       if(updateUser.getPassoword() != null){
        user.setPassoword(updateUser.getPassoword());
       }
       if(updateUser.getAdhaarNumber() != null){
        user.setAdhaarNumber(updateUser.getAdhaarNumber());
       }
       if(updateUser.getAccountType()!= null){
        user.setAccountType(updateUser.getAccountType());
    //     Branch branch = user.getBranch();
    //     branch.getPendingUserAccounts().add(user.getUserId());
    //     branchRepo.save(branch);
       }

       userRepo.save(user);
       return userToDTO(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getUserByBank(Integer bankId) throws BankException {
         return null;
    }

    @Override
    public List<User> getUserByBranch(Integer branchId) throws BranchException {
        Branch branch = branchRepo.findById(branchId).orElseThrow(() -> new BranchException("Branch with "+ branchId+" not found ."));
        return branch.getUsers();
    }

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
       User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with user id : "+ userId));
       return user;
    }   

    @Override
    public String deleteUserById(Long userId, String password) throws UserNotFoundException, PasswordException{
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with user id : "+ userId));
        if(!user.getPassoword().equals(password)){
            throw new PasswordException("Invalid password");
        }
        userRepo.delete(user);
        return "User Deleted ..";
    }


    public User dtoToUser(UserDTO dto){

        User user = new User();
        user.setAccountType(dto.getAccountType());
        user.setAccounts(dto.getAccounts());
        user.setAdhaarNumber(dto.getAdhaarNumber());
        user.setAge(dto.getAge());
        user.setBranch(dto.getBranch());
        user.setDepositeAmount(dto.getDepositeAmount());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setGender(dto.getGender());
        user.setPanCardNumber(dto.getPanCardNumber());
        user.setPhone(dto.getPhone());
        user.setUserId(dto.getUserId());
        user.setPassoword(dto.getPassoword());
        return user;
    }
    
    public UserDTO userToDTO(User dto){

        UserDTO user = new UserDTO();
        user.setAccountType(dto.getAccountType());
        user.setAccounts(dto.getAccounts());
        user.setAdhaarNumber(dto.getAdhaarNumber());
        user.setAge(dto.getAge());
        user.setBranch(dto.getBranch());
        user.setDepositeAmount(dto.getDepositeAmount());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setGender(dto.getGender());
        user.setPanCardNumber(dto.getPanCardNumber());
        user.setPhone(dto.getPhone());
        user.setUserId(dto.getUserId());
        user.setPassoword(dto.getPassoword());
        return user;
    }
}
