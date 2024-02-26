package com.learning.bankingService.repo;

import com.learning.bankingService.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    <Optional> java.util.Optional<Account> findByAccountNumber(String accountNumber);

//    Account findByAccountNumber(String accountNumber);
}
