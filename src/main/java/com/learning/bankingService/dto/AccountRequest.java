package com.learning.bankingService.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull(message = "Account type cannot be null")
    private AccountType accountType;

    @NotNull(message = "Account balance cannot be null")
    private Double accountBalance;

    private String approved = "no";
}
