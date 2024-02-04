package com.example.consumer.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message<U> implements Serializable {
    private LocalDateTime createdAt;
    private U data;
    private final LocalDateTime readAt = LocalDateTime.now();

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public U getData() {
        return data;
    }

    public void setData(U data) {
        this.data = data;
    }

    public Message(U data) {
        this.data = data;
    }

    public Message() {
    }
}
