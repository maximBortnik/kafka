package com.example.clientapp.dto;

import com.example.clientapp.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private double price;
    private Status status;
    private String customerName;
    private String phoneNumber;
    private String address;

    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime delivered;
}
