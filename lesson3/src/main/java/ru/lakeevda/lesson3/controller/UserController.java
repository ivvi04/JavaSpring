package ru.lakeevda.lesson3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.lesson3.entity.User;
import ru.lakeevda.lesson3.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping//localhost:8080/users
    public List<User> userList() {
        return registrationService.getDataProcessingService().getUsers();
    }

    @PostMapping("/body")//localhost:8080/users/body
    public String userAddFromBody(@RequestBody User user) {
        registrationService.processRegistration(user);
        return "User added from body!";
    }

    @PostMapping("/param")//localhost:8080/users/param?name=...&age=...&email=...
    public String userAddFromParam(@RequestParam String name, @RequestParam int age, @RequestParam String email) {
        registrationService.processRegistration(name, age, email);
        return "User added from param!";
    }
}