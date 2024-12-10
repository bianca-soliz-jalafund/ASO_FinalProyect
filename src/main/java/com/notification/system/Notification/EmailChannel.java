package com.notification.system.Notification;

import com.notification.system.Domain.Notification;
import com.notification.system.Logging.MonitoringLogger;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Email sent: " + notification.getMessage());
        MonitoringLogger.log("Email notification sent: " + notification.getMessage());
    }
}
