package com.example.consumer.controller;

import com.example.consumer.model.Message;
import com.example.consumer.service.KafkaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("get-all")
    public List<Message> publishMessage() {
       return kafkaService.getMessages();
    }
}
