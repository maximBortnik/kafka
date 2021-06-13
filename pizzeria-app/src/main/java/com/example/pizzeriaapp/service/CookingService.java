package com.example.pizzeriaapp.service;

import com.example.pizzeriaapp.dto.Order;
import com.example.pizzeriaapp.dto.Status;
import com.example.pizzeriaapp.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CookingService {

    private final OrderKafkaProducer kafkaProducer;

    @SneakyThrows
    public void cooking(Order order) {
        log.debug("Cooking order: {}", order);
        updateStatus(order, Status.COOKING);
        Thread.sleep(1200000);
        updateStatus(order, Status.READY);
    }

    private void updateStatus(Order order, Status status) {
        order.setStatus(status);
        order.setUpdated(LocalDateTime.now());
        kafkaProducer.sendMessage("Notification", order);
    }
}
