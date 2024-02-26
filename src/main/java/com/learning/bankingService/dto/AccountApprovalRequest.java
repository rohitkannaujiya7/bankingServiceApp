package com.learning.bankingService.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountApprovalRequest {

    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @NotBlank(message = "Approval status cannot be blank")
    private String approved;

}
