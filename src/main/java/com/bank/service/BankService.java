package com.bank.service;

import java.util.List;

import com.bank.dto.BankDTO;
import com.bank.dto.BranchDTO;
import com.bank.entities.Bank;
import com.bank.exception.BankException;
import com.bank.exception.BankNotFoundException;
import com.bank.exception.BranchException;

public interface BankService {
    
    public BankDTO addBank(BankDTO bankDto) throws BankException;

    public List<Bank> getAllBanks();

    public Bank getBankById(Integer id) throws BankNotFoundException;

    public BankDTO updateBank(BankDTO bankDto) throws BankNotFoundException;

    public String deleteBankById(Integer id) throws BankNotFoundException;

}
