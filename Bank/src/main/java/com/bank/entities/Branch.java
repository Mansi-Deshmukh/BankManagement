package com.bank.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;

    private String branchName;

    private String ifscCode;

    private String address;

    private String phone;

    private String branchEmail;

    // @Column(name = "applications")
    // private List<Long> pendingUserAccounts = new ArrayList<>();;


    @JsonIgnore
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL , fetch= FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
}
