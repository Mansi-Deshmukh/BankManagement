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

import com.bank.dto.BankDTO;
import com.bank.entities.Bank;
import com.bank.exception.BankException;
import com.bank.exception.BankNotFoundException;
import com.bank.service.BankService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/add")
    public ResponseEntity<BankDTO> addBankHandler(@RequestBody BankDTO bankDto) throws BankException{
       BankDTO bank = bankService.addBank(bankDto);
        return new ResponseEntity<BankDTO>(bank, HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Bank>> getAllBankHandler(){
       List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<List<Bank>>(banks, HttpStatus.OK);
    }

    @GetMapping("/bank/{bankId}")
    public ResponseEntity<Bank>  getBankByIdHandler(@PathVariable("bankId") Integer bankId) throws BankNotFoundException{
         Bank bank = bankService.getBankById(bankId);
         return new ResponseEntity<Bank>(bank, HttpStatus.OK);
     }

    @PutMapping("/update")
    public ResponseEntity<BankDTO> updateBankHandler(@RequestBody BankDTO bankDto) throws BankNotFoundException{
        BankDTO bank = bankService.updateBank(bankDto);
        return new ResponseEntity<BankDTO>(bank, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bankId}")
    public ResponseEntity<String> deleteBankHandler(@PathVariable("bankId") Integer bankId) throws BankNotFoundException{
        String message = bankService.deleteBankById(bankId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
