package com.example.consumer.dao;

import com.example.consumer.model.Message;

import java.util.List;

public interface MessageDAO {
    <T> void writeMessage(Message<T> message);
    <T> List<Message<T>> readAll();
}
