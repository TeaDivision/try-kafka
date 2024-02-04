package com.example.producer.model;

import java.time.LocalDateTime;

public class Message<U> {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private U data;

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
}
