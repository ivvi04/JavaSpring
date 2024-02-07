package ru.lakeevda.lesson3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson3.entity.User;

@Service
public class RegistrationService {

    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public void processRegistration(User user) {
        userService.saveUser(user);
        notificationService.sendNotification("User " + user.getName() + " save to Database");
    }

    public void processRegistration(String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        userService.saveUser(user);
        notificationService.sendNotification("User " + user.getName() + " save to Database");
    }
}