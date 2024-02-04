package com.example.producer.controller;

import com.example.producer.model.Message;
import com.example.producer.service.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<String> publishMessage(String msg) {
        try {
            kafkaService.publishMessage("test_topic", new Message<>(msg));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка при отправке сообщения:\n " + e.getMessage());
        }
        return ResponseEntity.ok("Сообщение отправлено");
    }
}
