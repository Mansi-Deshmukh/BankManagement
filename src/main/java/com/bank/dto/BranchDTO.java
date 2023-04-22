package com.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.bank.entities.Bank;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDTO {
    
    private Integer branchId;

    private String branchName;

    private String ifscCode;

    private String address;

    private String phone;

    private String branchEmail;

    @JsonIgnore
    private Bank bank;

    // private List<Long> pendingUserAccounts = new ArrayList<>();;
    // private List<User> users = new ArrayList<>();

    // private List<Employee> employees = new ArrayList<>();
}
