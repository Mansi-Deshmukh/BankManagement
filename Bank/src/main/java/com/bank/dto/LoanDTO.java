package com.bank.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.bank.entities.Account;
import com.bank.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanDTO {
    
    private Integer loanId;

    private Integer amount;

    private Integer interestRate;

    private int duration;
    
    private Account account;

    private String status;

    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();
}
