package com.bank.exception;

public class PaymentException extends Exception{
    public PaymentException(){

    }
    public PaymentException(String msg){
        super(msg);
    }
}
