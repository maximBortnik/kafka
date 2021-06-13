package com.example.pizzeriaapp.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private ObjectMapper objectMapper;
    private KafkaTemplate<String, String> kafkaTemplate;

    public <T> void sendMessage(String topic, T data) {
        log.debug("Send data: {} to a topic: {}", data, topic);
        kafkaTemplate.send(topic, toJson(data));
    }

    @SneakyThrows
    private <T> String toJson(T data) {
       return objectMapper.writeValueAsString(data);
    }
}
