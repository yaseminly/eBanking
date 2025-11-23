package org.example.transactionservice.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private double balance;
    private String type;
    private String owner;
}
