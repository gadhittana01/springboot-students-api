package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic barTopic(){
        return TopicBuilder.name("kafka_add_students")
                .partitions(4)
                .replicas(3)
                .build();
    }

    @Bean
    public KafkaListenerErrorHandler handler() {
        return new KafkaListenerErrorHandler() {
            @Override
            public Object handleError(org.springframework.messaging.Message<?> message,
                    ListenerExecutionFailedException exception) {
                System.out.println("message : "+message);
                System.out.println("exception : "+exception.getMessage());
                return null;
            }
        };
    }
}
