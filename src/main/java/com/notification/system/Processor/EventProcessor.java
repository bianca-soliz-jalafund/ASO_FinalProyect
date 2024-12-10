package com.notification.system.Processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import com.notification.system.Domain.Event;
import com.notification.system.Domain.Notification;
import com.notification.system.Logging.MonitoringLogger;

public class EventProcessor {
    private final BlockingQueue<Event> eventQueue;
    private final BlockingQueue<Notification> notificationQueue;

    public EventProcessor(BlockingQueue<Event> eventQueue, BlockingQueue<Notification> notificationQueue) {
        this.eventQueue = eventQueue;
        this.notificationQueue = notificationQueue;
    }

    public void processEvents() {
        Executors.newFixedThreadPool(4).submit(() -> {
            while (true) {
                try {
                    Event event = eventQueue.take();
                    MonitoringLogger.log("Processing event: " + event.getData());

                    String channel = determineChannel(event);
                    Notification notification = new Notification(channel, "Event: " + event.getData());

                    notificationQueue.put(notification);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private String determineChannel(Event event) {
        switch (event.getType()) {
            case "INFO":
                return "email";
            case "WARN":
            case "CRITICAL":
                return "sms";
            default:
                return "email";
        }
    }

}
