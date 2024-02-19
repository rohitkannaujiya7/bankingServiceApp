package com.learning.bankingService.config;

import com.learning.bankingService.entity.Customer;
import com.learning.bankingService.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerInfoCustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userInfo = customerRepository.findByUserName(username);
        return userInfo.map(CustomerInfoCustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
