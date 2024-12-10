package com.notification.system.Domain;

public class Notification {
    private final String channel;
    private final String message;

    public Notification(String channel, String message) {
        this.channel = channel;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }
}
