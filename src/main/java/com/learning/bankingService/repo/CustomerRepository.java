package com.learning.bankingService.repo;

import com.learning.bankingService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    <Optional> java.util.Optional<Customer> findByUserName(String userName);

}