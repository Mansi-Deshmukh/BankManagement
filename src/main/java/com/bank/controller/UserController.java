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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.UserDTO;
import com.bank.entities.User;
import com.bank.exception.BranchException;
import com.bank.exception.PasswordException;
import com.bank.exception.UserNotFoundException;
import com.bank.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/add/{branchId}")
    public ResponseEntity<UserDTO> addUserHandler(@RequestBody UserDTO userDto, @PathVariable("branchId") Integer branchId) throws BranchException{
        UserDTO user = userService.addUser(userDto, branchId);
         return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
     }

     @PutMapping("/update/{userId}/{password}")
     public ResponseEntity<UserDTO> updateUserHandler(@RequestBody UserDTO userDto, @PathVariable("userId") Long adhaarNumber,@PathVariable("password") String password) throws UserNotFoundException, PasswordException{
        UserDTO user = userService.updateUser(userDto, adhaarNumber,password);
         return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
     }

     @GetMapping("/all_user")
     public ResponseEntity<List<User>> getAllUserHandler(){
         List<User> userList = userService.getAllUser();
         return new ResponseEntity<List<User>>(userList, HttpStatus.CREATED);

     }

     @GetMapping("/branch_user/{branchId}")
     public ResponseEntity<List<User>> getAllUserByBranchHandler(@PathVariable("branchId") Integer branchId) throws BranchException{
         List<User> userList = userService.getUserByBranch(branchId);
         return new ResponseEntity<List<User>>(userList, HttpStatus.CREATED);

     }

     @GetMapping("/id/{userId}")
     public ResponseEntity<User> getUserByIdHandler(@PathVariable("userId") Long userId) throws UserNotFoundException  {
         User userList = userService.getUserById(userId);
         return new ResponseEntity<User>(userList, HttpStatus.CREATED);

     }

     @DeleteMapping("/delete/{userId}/{password}")
     public ResponseEntity<String> deleteUserHandler(@PathVariable("userId") Long userId, @PathVariable("password") String password) throws UserNotFoundException, PasswordException{
        String message = userService.deleteUserById(userId, password);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
    //  @PutMapping("/")
}
