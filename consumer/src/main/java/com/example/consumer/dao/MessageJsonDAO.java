package com.example.consumer.dao;

import com.example.consumer.model.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageJsonDAO implements MessageDAO {

    final File memory = new File("consumer-message-memory");

    @Autowired
    ObjectMapper mapper;

    @Override
    public <T> void writeMessage(Message<T> message) {
        try (FileWriter out = new FileWriter(memory, true)) {
            out.append(mapper.writeValueAsString(message)).append(System.lineSeparator());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<Message<T>> readAll() {
        List<Message<T>> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(memory))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.add(mapper.readValue(line, new TypeReference<>() {}));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostConstruct
    void initMemory() throws IOException {
        if (!memory.exists()) {
            memory.createNewFile();
            System.out.println("Memory file for MessageJsonDAO is created!");
        }
        memory.deleteOnExit();
    }
}
