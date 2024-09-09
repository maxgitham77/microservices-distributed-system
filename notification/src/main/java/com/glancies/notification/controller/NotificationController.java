package com.glancies.notification.controller;

import com.glancies.clients.notification.NotificationRequest;
import com.glancies.notification.service.SendNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notification")
@Slf4j
public class NotificationController {

    @Autowired
    private SendNotificationService sendNotificationService;

    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification...{}", notificationRequest);
        sendNotificationService.sendNotification(notificationRequest);
    }

}
