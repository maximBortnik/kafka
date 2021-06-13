package com.example.clientapp.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic order() {
        return TopicBuilder.name("Order")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notification() {
        return TopicBuilder.name("Notification")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
