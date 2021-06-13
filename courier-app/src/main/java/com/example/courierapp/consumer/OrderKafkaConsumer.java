package com.example.courierapp.consumer;


import com.example.courierapp.dto.Order;
import com.example.courierapp.dto.Status;
import com.example.courierapp.service.DeliveringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final DeliveringService cookingService;

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "0"))
    public void partition1(@Payload String data,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.debug("Received order: {} from topic: {}, partition: 0", data, topic);
        handle(data);
    }

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "1"))
    public void partition2(@Payload String data,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.debug("Received order: {} from topic: {}, partition: 1", data, topic);
        handle(data);
    }

    @KafkaListener(groupId = "orderGroup", topicPartitions = @TopicPartition(topic = "Notification", partitions = "2"))
    public void partition3(@Payload String data,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.debug("Received order: {} from topic: {}, partition: 2", data, topic);
        handle(data);
    }

    private void handle(String orderStr) {
        var order = fromData(orderStr);
        if (Status.READY == order.getStatus()) {
            cookingService.cooking(order);
        }
    }

    @SneakyThrows
    private Order fromData(String data) {
        return objectMapper.readValue(data, Order.class);
    }

}
