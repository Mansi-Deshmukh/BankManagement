package com.bank.exception;



public class LoanException extends RuntimeException {
    public LoanException(){
        
    }  
    public LoanException(String msg){
        super(msg);
    }
}
