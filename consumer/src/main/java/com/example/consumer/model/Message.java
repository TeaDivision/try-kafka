package com.example.consumer.model;

import java.time.LocalDateTime;

public class Message {
    LocalDateTime readAt;
    String message;

    public Message(LocalDateTime readAt, String message) {
        this.readAt = readAt;
        this.message = message;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
