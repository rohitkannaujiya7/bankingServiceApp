package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.advice.UnauthorizedAccessException;
import com.learning.bankingService.dto.AccountRequest;
import com.learning.bankingService.entity.Account;
import com.learning.bankingService.entity.Customer;
import com.learning.bankingService.repo.AccountRepository;
import com.learning.bankingService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerID));


        if (!authenticatedUsername.equals(customer.getUserName())) {
            throw new UnauthorizedAccessException("Unauthorized access to customer's account");
        }

        Account account = Account.build(null,accountRequest.getAccountType(),accountRequest.getAccountBalance()
                ,accountRequest.getApproved(),new Date(),customerID);
        return accountRepository.save(account);
    }

    @Override
    public Account approveAccount(String accountNumber, String approved) throws AccountNotFoundException {
        // Retrieve the account by customer ID and account number
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for customer"));

        // Update the approval status
        account.setApproved(approved);

        // Save the updated account
        return accountRepository.save(account);
    }


    @Override
    public List<Account> getAllAccounts(Long customerId) {
        return null;
    }
}