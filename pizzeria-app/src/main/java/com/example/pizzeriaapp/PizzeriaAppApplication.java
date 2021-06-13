package com.example.pizzeriaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PizzeriaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaAppApplication.class, args);
	}

}
