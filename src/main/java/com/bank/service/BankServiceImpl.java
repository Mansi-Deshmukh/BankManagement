package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.BankDTO;
import com.bank.dto.BranchDTO;
import com.bank.entities.Bank;
import com.bank.entities.Branch;
import com.bank.exception.BankException;
import com.bank.exception.BankNotFoundException;
import com.bank.exception.BranchException;
import com.bank.repository.BankRepo;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BankRepo bankRepo;

    @Override
    public BankDTO addBank(BankDTO bankDto) throws BankException {
       Bank bank = bankRepo.findByBankName(bankDto.getBankName());
       if(bank != null){
        throw new BankException("Bank already registered ..");
       }
       Bank addBank = dtoToBank(bankDto);
       
       bankRepo.save(addBank);
        return bankToDto(addBank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepo.findAll();
        // return bankRepo.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Bank getBankById(Integer bankId) throws BankNotFoundException {
        return bankRepo.findById(bankId).orElseThrow(() -> new BankNotFoundException("Bank with id " + bankId + " not found."));

    }

    @Override
    public BankDTO updateBank(BankDTO bank) throws BankNotFoundException { 
        Bank b = dtoToBank(bank);
        Bank update = bankRepo.findById(b.getBankId()).orElseThrow(()-> new BankNotFoundException("Bank with id " + b.getBankId()+ " not found."));
        // if(update == null)
        //    throw new BankNotFoundException("Bank with id " + b.getBankId()+ " not found.");
        bankRepo.save(b);
        return bank;
    }

    @Override
    public String deleteBankById(Integer bankId) throws BankNotFoundException {
        Bank bank = bankRepo.findById(bankId).orElseThrow(()-> new BankNotFoundException("Bank with id " + bankId+ " not found."));
        bankRepo.delete(bank);
        return " Bank Deleted ...";
    }
    


    public Bank dtoToBank(BankDTO bank){

        Bank b = new Bank();
        b.setBankName(bank.getBankName());
        b.setBranches(bank.getBranches());
        b.setBankId(bank.getBankId());

        return b;
    }

    public BankDTO bankToDto(Bank bank){

        BankDTO b = new BankDTO();
        b.setBankName(bank.getBankName());
        b.setBranches(bank.getBranches());
        b.setBankId(bank.getBankId());

        return b;
    }

    

   
}
