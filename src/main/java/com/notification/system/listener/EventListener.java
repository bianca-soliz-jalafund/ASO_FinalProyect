package com.notification.system.listener;

import com.notification.system.Domain.Event;
import com.notification.system.Logging.MonitoringLogger;

import java.util.concurrent.BlockingQueue;

public class EventListener {
    private final BlockingQueue<Event> eventQueue;

    public EventListener(BlockingQueue<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public void receiveEvent(Event event) {
        try {
            eventQueue.put(event);
            MonitoringLogger.log("Event received: " + event.getData());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
