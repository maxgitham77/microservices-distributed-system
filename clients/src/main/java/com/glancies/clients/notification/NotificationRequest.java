package com.glancies.clients.notification;

public record NotificationRequest(
        String message,
        String sender,
        String toCustomerEmail,
        Long toCustomerId

) {

}
