package com.learning.bankingService.repo;

import com.learning.bankingService.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Long> {
}
