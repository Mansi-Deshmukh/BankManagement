package com.bank.exception;

public class BankNotFoundException extends Exception {
    public BankNotFoundException(){
        
    }
    public BankNotFoundException(String msg){
        super(msg);
    }
}
