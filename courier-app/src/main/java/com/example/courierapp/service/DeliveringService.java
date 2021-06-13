package com.example.courierapp.service;

import com.example.courierapp.dto.Order;
import com.example.courierapp.dto.Status;
import com.example.courierapp.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveringService {

    private final OrderKafkaProducer kafkaProducer;

    @SneakyThrows
    public void cooking(Order order) {
        log.debug("Delivering order: {}", order);
        updateStatus(order, Status.DELIVERING);
        Thread.sleep(1200000);
        updateStatus(order, Status.DELIVERED);
    }

    private void updateStatus(Order order, Status status) {
        order.setStatus(status);
        order.setUpdated(LocalDateTime.now());
        if (Status.DELIVERED == status) {
            order.setDelivered(LocalDateTime.now());
        }
        kafkaProducer.sendMessage("Notification", order);
    }
}
