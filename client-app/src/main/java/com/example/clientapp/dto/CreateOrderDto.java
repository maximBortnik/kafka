package com.example.clientapp.dto;

import com.example.clientapp.dto.validation.Phone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderDto {
    @NotBlank
    private String name;
    @NotNull
    private double price;
    @NotBlank
    private String customerName;
    @Phone
    private String phoneNumber;
    @NotBlank
    private String address;
}
