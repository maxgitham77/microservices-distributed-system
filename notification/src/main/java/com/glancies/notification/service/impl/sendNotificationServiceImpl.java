package com.glancies.notification.service.impl;

import com.glancies.clients.notification.NotificationRequest;
import com.glancies.notification.entity.Notification;
import com.glancies.notification.repository.NotificationRepository;
import com.glancies.notification.service.SendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class sendNotificationServiceImpl implements SendNotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender(notificationRequest.sender())
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
        return null;
    }
}
