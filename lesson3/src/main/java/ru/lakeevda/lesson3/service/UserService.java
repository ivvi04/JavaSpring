package ru.lakeevda.lesson3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson3.entity.User;
import ru.lakeevda.lesson3.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);

        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
