package com.example.clientapp.service;

import com.example.clientapp.kafka.producer.OrderKafkaProducer;
import com.example.clientapp.model.Order;
import com.example.clientapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderKafkaProducer kafkaProducer;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrUpdate(Order order) {
        if (order.getId() == null) {
            var result = orderRepository.save(order);
            kafkaProducer.sendMessage("Order", result);
            return result;
        } else {
            return orderRepository.save(order);
        }
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id: %s does not exist", id)));
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
