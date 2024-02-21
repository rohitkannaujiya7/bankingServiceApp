package com.learning.bankingService.controller;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;
import com.learning.bankingService.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/{customerID}/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping
    public ResponseEntity<?> createAccount(@PathVariable Long customerID, @RequestBody @Valid AccountRequest accountRequest) throws CustomerNotFoundException {
        System.out.println("Received Account Request Details:");
        System.out.println( accountRequest.getAccountType());
        System.out.println(accountRequest.getAccountBalance());
        System.out.println(accountRequest.getApproved());
        Account account = accountService.createAccount(customerID, accountRequest);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
