package com.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.entities.Loan;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {
    
    private Long paymentId;

    private Integer amount;

    private LocalDate paymentDate;

    private Loan loan;
}
