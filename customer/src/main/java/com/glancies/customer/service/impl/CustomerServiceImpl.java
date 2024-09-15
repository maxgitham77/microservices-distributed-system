package com.glancies.customer.service.impl;

import com.glacies.ampq.RabbitMQMessageProducer;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    private RestTemplate restTemplate;

    @Autowired
    private final FraudClient fraudClient;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

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
        NotificationRequest notificationRequest = new NotificationRequest(
                /*customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Glancies...", customer.getFirstName())*/
                "First",
                "First",
                "First",
                1L
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
                );


        return null;
    }

}
