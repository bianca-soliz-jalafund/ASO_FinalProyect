package com.notification.system;

import com.notification.system.Domain.Event;
import com.notification.system.Domain.Notification;
import com.notification.system.listener.EventListener;
import com.notification.system.Processor.EventProcessor;
import com.notification.system.Dispatcher.NotificationDispatcher;
import com.notification.system.Notification.EmailChannel;
import com.notification.system.Notification.SmsChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncNotificationSystem {
    public static void main(String[] args) {
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Notification> notificationQueue = new LinkedBlockingQueue<>();

        EventListener eventListener = new EventListener(eventQueue);
        EventProcessor eventProcessor = new EventProcessor(eventQueue, notificationQueue);
        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(notificationQueue);

        notificationDispatcher.registerChannel("email", new EmailChannel());
        notificationDispatcher.registerChannel("sms", new SmsChannel());

        eventProcessor.processEvents();
        notificationDispatcher.dispatchNotifications();

        // Simulate events
        eventListener.receiveEvent(new Event("1", "INFO", "User registered"));
        eventListener.receiveEvent(new Event("2", "WARN", "Disk space low"));
    }
}
