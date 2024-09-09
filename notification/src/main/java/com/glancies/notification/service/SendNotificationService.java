package com.glancies.notification.service;

import com.glancies.clients.notification.NotificationRequest;
import com.glancies.notification.entity.Notification;

public interface SendNotificationService {

    Notification sendNotification(NotificationRequest notificationRequest);
}
