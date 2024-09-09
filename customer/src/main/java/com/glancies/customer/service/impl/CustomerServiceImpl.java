package com.glancies.customer.service.impl;

import com.glancies.clients.fraud.FraudCheckResponse;
import com.glancies.clients.fraud.FraudClient;
import com.glancies.clients.notification.NotificationClient;
import com.glancies.clients.notification.NotificationRequest;
import com.glancies.customer.entity.Customer;
import com.glancies.customer.repository.CustomerRepository;
import com.glancies.customer.request.CustomerRegistrationRequest;
import com.glancies.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private RestTemplate restTemplate;

    @Autowired
    private FraudClient fraudClient;

    @Autowired
    private NotificationClient notificationClient;

    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        /*FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud/check/{customerId}",
                    FraudCheckResponse.class,
                    customer.getId()
        );*/

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("This is a fraudster in action");
        }

        // TODO: make it async - add to queue
        //notificationClient.sendNotification();


        return null;
    }

}
