package com.learning.bankingService.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest{
    @NotNull(message = "userName shouldn't be null")
    private String userName;
    @NotNull(message = "fullName shouldn't be null")
    private String fullName;
    @NotNull(message = "password shouldn't be null")
    private String password;
    private String roles;
}
