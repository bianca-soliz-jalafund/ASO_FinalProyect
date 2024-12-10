package com.notification.system.Notification;

import com.notification.system.Domain.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}
