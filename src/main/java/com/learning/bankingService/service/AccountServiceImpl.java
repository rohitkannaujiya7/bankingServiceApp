package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;
import com.learning.bankingService.entity.Customer;
import com.learning.bankingService.repo.AccountRepository;
import com.learning.bankingService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account createAccount(Long customerId, AccountRequest accountRequest) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        Account account = new Account();
        account.setAccountType(accountRequest.getAccountType());
        account.setAccountBalance(accountRequest.getAccountBalance());
        account.setApproved(accountRequest.getApproved());
        account.setDateOfCreation(new Date());
        account.setCustomerId(customer);

        return accountRepository.save(account);
    }
}