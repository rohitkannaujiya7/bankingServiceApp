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
    public Account createAccount(Long customerID, AccountRequest accountRequest) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerID));

        Account account = Account.build(0L,accountRequest.getAccountType(),accountRequest.getAccountBalance()
                ,accountRequest.getApproved(),new Date(),customerID);


        return accountRepository.save(account);
    }
}