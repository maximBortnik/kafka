package com.example.clientapp.controller;

import com.example.clientapp.dto.CreateOrderDto;
import com.example.clientapp.dto.OrderDto;
import com.example.clientapp.model.Order;
import com.example.clientapp.model.Status;
import com.example.clientapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto orderDto) {
        var order = Order.builder()
                .name(orderDto.getName())
                .price(orderDto.getPrice())
                .status(Status.NEW)
                .customerName(orderDto.getCustomerName())
                .phoneNumber(orderDto.getPhoneNumber())
                .address(orderDto.getAddress())
                .build();
        var result = orderService.createOrUpdate(order);
        return ResponseEntity.ok(convertToOrderDot(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@RequestParam Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(convertToOrderDot(order));
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> findAll() {
        var orderDtos = orderService.findAll()
                .stream()
                .map(this::convertToOrderDot)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    private OrderDto convertToOrderDot(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .name(order.getName())
                .price(order.getPrice())
                .status(order.getStatus())
                .created(order.getCreated())
                .updated(order.getUpdated())
                .delivered(order.getDelivered())
                .build();
    }
}
