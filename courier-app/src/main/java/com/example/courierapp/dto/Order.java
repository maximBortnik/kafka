package com.example.courierapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

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
