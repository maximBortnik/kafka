package com.example.clientapp.kafka.consumer;

import com.example.clientapp.model.Order;
import com.example.clientapp.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "0"))
    public void partition1(@Payload String data) {
        log.debug("Received order: {} from topic: Order, partition: 0", data);
        handle(data);
    }

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "1"))
    public void partition2(@Payload String data) {
        log.debug("Received order: {} from topic: Order, partition: 1", data);
        handle(data);
    }

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "2"))
    public void partition3(@Payload String data) {
        log.debug("Received order: {} from topic: Order, partition: 2", data);
        handle(data);
    }

    private void handle(String order) {
        orderService.createOrUpdate(fromData(order));
    }

    @SneakyThrows
    private Order fromData(String data) {
        return objectMapper.readValue(data, Order.class);
    }

}
