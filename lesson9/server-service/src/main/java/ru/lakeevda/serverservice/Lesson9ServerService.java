package ru.lakeevda.serverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Lesson9ServerService {

    public static void main(String[] args) {
        SpringApplication.run(Lesson9ServerService.class, args);
    }

}
