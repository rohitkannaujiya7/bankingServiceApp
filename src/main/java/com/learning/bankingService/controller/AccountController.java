package com.learning.bankingService.controller;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountApprovalRequest;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;
import com.learning.bankingService.repo.AccountRepository;
import com.learning.bankingService.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/customer/{customerID}/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;


    @PostMapping
    public ResponseEntity<?> createAccount(@PathVariable Long customerID, @RequestBody @Valid AccountRequest accountRequest) throws CustomerNotFoundException {
        try{
            Account account = accountService.createAccount(customerID, accountRequest);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/accountNo")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<?> approveAccount(@RequestBody AccountApprovalRequest approvalRequest) {
        System.out.println(approvalRequest.getAccountNumber());
        if (!approvalRequest.getAccountNumber().equals(accountRepository.findByAccountNumber(approvalRequest.getAccountNumber()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check Account Number");
        }

        try {
            Account approvedAccount = accountService.approveAccount(approvalRequest.getAccountNumber(), approvalRequest.getApproved());
            return ResponseEntity.ok(approvedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
