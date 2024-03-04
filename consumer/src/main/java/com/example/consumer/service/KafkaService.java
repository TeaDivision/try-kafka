package com.example.consumer.service;

import com.example.consumer.dao.MessageDAO;
import com.example.consumer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService {

    @Autowired
    @Qualifier("messageJsonDAO")
    MessageDAO dao;

    @KafkaListener(id = "test", topicPattern = "test_topic", groupId = "test_group")
    public void receiveMessage(@Payload Message<String> message) {
        dao.writeMessage(message);
    }

    public List<Message<Object>> getMessages() {
        return dao.readAll();
    }
}
