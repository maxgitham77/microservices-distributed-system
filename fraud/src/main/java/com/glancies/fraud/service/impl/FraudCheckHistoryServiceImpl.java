package com.glancies.fraud.service.impl;

import com.glancies.fraud.entity.FraudCheckHistory;
import com.glancies.fraud.repository.FraudCheckHistoryRepository;
import com.glancies.fraud.service.FraudCheckHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryServiceImpl implements FraudCheckHistoryService {

    @Autowired
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Override
    public boolean isFraudulentCustomer(Long customerId) {
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        return false;
    }
}
