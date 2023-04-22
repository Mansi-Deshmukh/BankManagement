package com.bank.exception;

public class AccountException extends Exception {
    public AccountException(){
        
    }  

    public AccountException(String msg){
        super(msg);
    }
}
