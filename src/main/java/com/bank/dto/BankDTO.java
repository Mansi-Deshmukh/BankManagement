package com.bank.dto;

import java.util.ArrayList;
import java.util.List;

import com.bank.entities.Branch;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankDTO {
    private Integer bankId;

    private String bankName;

    private List<Branch> branches = new ArrayList<>();
}
