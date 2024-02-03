package com.example.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @KafkaListener(id = "test", topicPattern = "test_topic", groupId = "test_group")
    public void receiveMessage(String data) {
        System.out.println(data);
    }
}
