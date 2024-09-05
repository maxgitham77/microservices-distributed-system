package com.glancies.customer.service;

import com.glancies.customer.entity.Customer;
import com.glancies.customer.request.CustomerRegistrationRequest;

public interface CustomerService {
    Customer registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);
}
