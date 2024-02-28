package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {
    Account createAccount(Long customerID, AccountRequest accountRequest) throws CustomerNotFoundException;
    // AccountService.java

    Account approveAccount(String accountNumber, String approved) throws AccountNotFoundException;

    List<Account> getAllAccounts(Long customerID);

}