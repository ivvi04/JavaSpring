package ru.lakeevda.kafkaintegration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.kafkaintegration.dto.User;
import ru.lakeevda.kafkaintegration.service.KafkaProducerService;

@RestController
@RequiredArgsConstructor
public class KafkaUserController {
    private final KafkaProducerService producerService;

    @PostMapping("/messages")
    public void sendMessageToKafka(@RequestParam Long msgid, @RequestBody User user) {
        producerService.sendMessage(msgid, user);
    }
}
