package com.example.consumer.service;

import com.example.consumer.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class KafkaService {

    List<Message> messages = new ArrayList<>();

    @KafkaListener(id = "test", topicPattern = "test_topic", groupId = "test_group")
    public void receiveMessage(String data) {
        messages.add(new Message(LocalDateTime.now(), data));
        System.out.println(data);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
