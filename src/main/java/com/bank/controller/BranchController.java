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

import com.bank.dto.BranchDTO;
import com.bank.entities.Branch;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.bank.exception.BankException;
import com.bank.exception.BranchException;
import com.bank.exception.EmployeeException;
import com.bank.exception.UserNotFoundException;
import com.bank.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
    
    @Autowired
    private BranchService branchService;


    @PostMapping("/add/{bankId}")
    public ResponseEntity<BranchDTO> addBranchHandler(@PathVariable("bankId") Integer bankId, @RequestBody BranchDTO branchDto) throws BranchException, BankException {
         BranchDTO branch = branchService.addBranch(bankId, branchDto);
        return new ResponseEntity<BranchDTO>(branch, HttpStatus.CREATED);
    }

    // @GetMapping("/all/{bankId}")
    // public ResponseEntity<List<Branch>> getAllBranchesBYBankHandler(@PathVariable("bankId") Integer bankId){
    //     List<Branch> list =branchService.getBranchById(bankId);
    //     return new ResponseEntity<List<Branch>>(list, HttpStatus.OK);
    // }

    // @GetMapping("/all")
    // public ResponseEntity<List<Branch>> getAllBranchesHandler(){
    //     List<Branch> list =branchService.getAllBranches();
    //     return new ResponseEntity<List<Branch>>(list, HttpStatus.OK);
    // }
    @GetMapping("/count/{bankId}")
    public ResponseEntity<Integer> getCountHandler(@PathVariable("bankId") Integer bankId) throws BankException{
        Integer list =branchService.countBranches(bankId);
        return new ResponseEntity<Integer>(list, HttpStatus.OK);
    }

    // @GetMapping("/all/{bankId}")
    // public ResponseEntity<List<Branch>> getAllBranchesByBankHandler(@PathVariable("bankId") Integer bankId) throws BankException, BranchException{
    //     List<Branch> list =branchService.getAllBranchesByBank(bankId);
    //     return new ResponseEntity<List<Branch>>(list, HttpStatus.OK);
    // }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<Branch> getBrancheByIdHandler(@PathVariable("branchId") Integer branchId) throws BranchException{
        Branch branch =branchService.getBranchById(branchId);
        return new ResponseEntity<Branch>(branch, HttpStatus.OK);
    }

    @GetMapping("/user/{branchId}")
    public ResponseEntity<List<User>> getAllUserHandler(@PathVariable("branchId") Integer branchId) throws BranchException, UserNotFoundException{
        List<User> list =branchService.getUsersByBranchId(branchId);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
    @GetMapping("/employee/{branchId}")
    public ResponseEntity<List<Employee>> getAllEmployeeHandler(@PathVariable("branchId") Integer branchId) throws BranchException, EmployeeException{
        List<Employee> list =branchService.getEmployeesByBranchId(branchId);
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

    @PutMapping("/update/{branchId}")
    public ResponseEntity<BranchDTO> updateBranchHandler(@RequestBody BranchDTO bDto, @PathVariable Integer branchId) throws BranchException, BankException {
        BranchDTO branch = branchService.updateBranch(bDto, branchId);
        return new ResponseEntity<BranchDTO>(branch, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{branchId}")
    public ResponseEntity<String> deleteBranchByIdHandler(@PathVariable("branchId") Integer branchId) throws BranchException{
        String message = branchService.deleteBranchById(branchId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping("/branch_names/{bankId}")
    public ResponseEntity<List<String>> getBranchNamesHandler(@PathVariable("bankId") Integer bankId) throws BankException{
        List<String> list = branchService.getBrachNameByBank(bankId);
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/all_branch_bank/{bankId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchesDTOByBankHandler(@PathVariable("bankId") Integer bankId) throws BankException, BranchException{
        List<BranchDTO> list =branchService.getBranchesByBank(bankId);
        return new ResponseEntity<List<BranchDTO>>(list, HttpStatus.OK);
    }
}
