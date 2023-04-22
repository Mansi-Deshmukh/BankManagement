package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entities.Payment;
import com.bank.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add_payment/{amount}/{loanId}")
    public ResponseEntity<Payment> addPaymentHandler(@PathVariable("amount") double amount , @PathVariable("loanId") Integer loanId){
        Payment payment = paymentService.repayLoan(amount, loanId);
        return new ResponseEntity<Payment>(payment, HttpStatus.CREATED);
    }

    

}
