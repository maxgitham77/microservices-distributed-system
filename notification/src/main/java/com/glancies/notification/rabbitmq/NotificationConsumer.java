package com.glancies.notification.rabbitmq;

import com.glancies.clients.notification.NotificationRequest;
import com.glancies.notification.service.SendNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final SendNotificationService sendNotificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        sendNotificationService.sendNotification(notificationRequest);
    }

}
