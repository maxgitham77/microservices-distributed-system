package com.glancies.customer.service.impl;

import com.glancies.customer.entity.Customer;
import com.glancies.customer.repository.CustomerRepository;
import com.glancies.customer.request.CustomerRegistrationRequest;
import com.glancies.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
        return null;
    }

}
