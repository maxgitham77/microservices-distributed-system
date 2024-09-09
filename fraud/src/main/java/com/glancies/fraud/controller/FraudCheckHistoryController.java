package com.glancies.fraud.controller;

import com.glancies.clients.fraud.FraudCheckResponse;
import com.glancies.fraud.service.FraudCheckHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud")
@AllArgsConstructor
@Slf4j
public class FraudCheckHistoryController {

    @Autowired
    private FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "/check/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("Fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
