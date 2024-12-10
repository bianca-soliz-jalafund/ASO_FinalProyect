package com.notification.system.Dispatcher;

import com.notification.system.Domain.Notification;
import com.notification.system.Logging.MonitoringLogger;
import com.notification.system.Notification.NotificationChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

public class NotificationDispatcher {
    private final BlockingQueue<Notification> notificationQueue;
    private final ConcurrentMap<String, NotificationChannel> channels;

    public NotificationDispatcher(BlockingQueue<Notification> notificationQueue) {
        this.notificationQueue = notificationQueue;
        this.channels = new ConcurrentHashMap<>();
    }

    public void registerChannel(String channelName, NotificationChannel channel) {
        channels.put(channelName, channel);
        MonitoringLogger.log("Channel registered: " + channelName);
    }

    public void dispatchNotifications() {
        Executors.newFixedThreadPool(4).submit(() -> {
            while (true) {
                try {
                    Notification notification = notificationQueue.take();
                    NotificationChannel channel = channels.get(notification.getChannel());
                    if (channel != null) {
                        channel.send(notification);
                    } else {
                        MonitoringLogger.log("No channel found for: " + notification.getChannel());
                        System.out.println("No channel found for: " + notification.getChannel());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}
