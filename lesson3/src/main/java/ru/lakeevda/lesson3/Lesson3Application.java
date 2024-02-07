package ru.lakeevda.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson3Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson3Application.class, args);
    }
//
//    @Bean
//    public void createUser () {
//        UserRepository userRepository = new UserRepository();
//        User user1 = new User();
//        user1.setName("Denis");
//        user1.setAge(34);
//        user1.setEmail("denisemail@mail.ru");
//        userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setName("Anna");
//        user2.setAge(32);
//        user2.setEmail("annaemail@mail.ru");
//        userRepository.save(user2);
//    }
}
