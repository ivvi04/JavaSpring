package ru.lakeevda.kafkaintegration.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.lakeevda.kafkaintegration.dto.User;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "user-topic", groupId = "user-group")
    public void receiveMessage(ConsumerRecord<Long, User> record) {
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
    }
}