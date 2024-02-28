package com.learning.bankingService.controller;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountApprovalRequest;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.dto.AccountResponse;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer/{customerID}/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;


    @PostMapping
    public ResponseEntity<?> createAccount(@PathVariable Long customerID, @RequestBody @Valid AccountRequest accountRequest) throws CustomerNotFoundException {
        try {
            Account account = accountService.createAccount(customerID, accountRequest);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/accountNo")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<?> approveAccount(@RequestBody AccountApprovalRequest approvalRequest) {

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(approvalRequest.getAccountNumber());
        if (existingAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not found for the given Account Number");
        }

        try {

            Account approvedAccount = accountService.approveAccount(approvalRequest.getAccountNumber(), approvalRequest.getApproved());
            return ResponseEntity.ok(approvedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAccounts(@PathVariable Long customerID) {
        List<Account> accounts = accountService.getAllAccounts(customerID);
        List<AccountResponse> accountResponses = accounts.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountResponses);
    }

    private AccountResponse mapToResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setAccountBalance(account.getAccountBalance());
        response.setAccountStatus(account.getApproved());
        return response;
    }

    }
