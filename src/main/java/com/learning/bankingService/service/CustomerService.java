package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.CustomerRequest;
import com.learning.bankingService.entity.Customer;

public interface CustomerService {

    public Customer saveCustomer(CustomerRequest customerRequest);

    Customer getCustomerById(Long customerId) throws CustomerNotFoundException;
}
