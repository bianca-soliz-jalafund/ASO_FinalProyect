package com.notification.system.Notification;

import com.notification.system.Domain.Notification;
import com.notification.system.Logging.MonitoringLogger;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("SMS sent: " + notification.getMessage());
        MonitoringLogger.log("SMS notification sent: " + notification.getMessage());
    }
}
