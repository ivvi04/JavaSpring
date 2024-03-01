package ru.lakeevda.shopclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Lesson9ShopClient {

    public static void main(String[] args) {
        SpringApplication.run(Lesson9ShopClient.class, args);
    }

}
