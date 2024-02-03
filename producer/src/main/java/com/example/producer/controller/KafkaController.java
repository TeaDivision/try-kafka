package com.example.producer.controller;

import com.example.producer.service.KafkaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class KafkaController {
    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping
    public String publishMessage(String msg) {
        try {
            kafkaService.publishMessage("test_topic", msg);
        }
        catch (Exception e) {
            return "Ошибка при отправке сообщения:\n " + e.getMessage();
        }
        return "Сообщение отправлено";
    }
}
