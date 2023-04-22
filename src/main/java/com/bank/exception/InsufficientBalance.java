package com.bank.exception;

public class InsufficientBalance extends Exception{
    public InsufficientBalance(){
        
    }  
    public InsufficientBalance(String msg){
        super(msg);
    }
}
