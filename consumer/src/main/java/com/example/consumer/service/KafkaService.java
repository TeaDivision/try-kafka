package com.example.consumer.service;

import com.example.consumer.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaService {

    List<Message<?>> messages = new ArrayList<>();

    @KafkaListener(id = "test", topicPattern = "test_topic", groupId = "test_group")
    public void receiveMessage(@Payload Message<String> message) {
        messages.add(message);
    }

    public List<Message<?>> getMessages() {
        return messages;
    }
}
