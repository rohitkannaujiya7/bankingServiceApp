package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;

public interface AccountService {
    Account createAccount(Long customerId, AccountRequest accountRequest) throws CustomerNotFoundException;
//    Account approveAccount(Long customerId, Long accountNumber);
//    List<Account> getAllAccounts(Long customerId);
}