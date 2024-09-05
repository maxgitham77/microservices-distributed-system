package com.glancies.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @PostMapping("/register")
    public void registerCustomer() {
        log.info("Register");
    }

}
