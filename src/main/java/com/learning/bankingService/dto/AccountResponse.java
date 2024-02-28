package com.learning.bankingService.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {
    private String accountNumber;
    private AccountType accountType;
    private Double accountBalance;
    private String accountStatus;

    public AccountResponse(String accountNumber, AccountType accountType, Double accountBalance, String approved, Date dateOfCreation, Long customerID) {

    }

}
