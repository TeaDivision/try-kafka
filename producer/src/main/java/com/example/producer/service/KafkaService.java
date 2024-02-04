package com.example.producer.service;

import com.example.producer.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Message<?>> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Message<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage(String topic, Message<?> message) {
        kafkaTemplate.send(topic, message);
    }
}
