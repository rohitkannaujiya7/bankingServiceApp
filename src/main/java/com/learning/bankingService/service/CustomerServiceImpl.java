package com.learning.bankingService.service;

import com.learning.bankingService.ExceptionHandler.CustomerNotFoundException;
import com.learning.bankingService.dto.CustomerRequest;
import com.learning.bankingService.entity.Customer;
import com.learning.bankingService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {

        Customer customer = Customer.build(0L, customerRequest.getUserName(),
                customerRequest.getFullName(),customerRequest.getPassword(), customerRequest.getRoles());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerId + " not found"));
    }
}
