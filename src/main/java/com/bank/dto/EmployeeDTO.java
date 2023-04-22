package com.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.bank.entities.Account;
import com.bank.entities.Branch;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
  
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @JsonIgnore
    private Branch branch;

    private String passoword;
    // private List<Account> accounts = new ArrayList<>();

    // private List<Integer> pendingUserAccounts;
}
