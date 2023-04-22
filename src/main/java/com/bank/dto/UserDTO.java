package com.bank.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Enumerated;

import com.bank.entities.Account;
import com.bank.entities.AccountType;
import com.bank.entities.Branch;
import com.bank.entities.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String gender; 

    private double depositeAmount;

    private Long adhaarNumber;

    private String panCardNumber ;

    private Integer age;

    private String passoword;

    @Enumerated
    private AccountType accountType;

    @JsonIgnore
    private Branch branch;

    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

}
