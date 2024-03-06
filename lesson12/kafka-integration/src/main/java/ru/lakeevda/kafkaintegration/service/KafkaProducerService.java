package ru.lakeevda.kafkaintegration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.lakeevda.kafkaintegration.dto.User;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, User> kafkaTemplate;

    public void sendMessage(Long msgId, User user) {
        CompletableFuture<SendResult<Long, User>> future = kafkaTemplate.send("user-topic", msgId, user);
        future.whenComplete((sr, ex) -> {
            if (sr != null){
                System.out.println("success");
            } else if (ex != null){
                System.out.println("fail");
            }
        });
        kafkaTemplate.flush();
    }
}
