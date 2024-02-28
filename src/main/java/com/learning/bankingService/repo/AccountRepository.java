package com.learning.bankingService.repo;

import com.learning.bankingService.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerID(Long customerID);
}