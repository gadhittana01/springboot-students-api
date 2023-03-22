package com.example.repositories;

import org.springframework.messaging.Message;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaRepository<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaRepository.class);
    
    @Autowired
    public KafkaRepository(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(T data, String topic){
        Message<T> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,topic).build();
        kafkaTemplate.send(message);
        LOGGER.info(String.format("Message sent : %s",message.toString()));
    }
}
