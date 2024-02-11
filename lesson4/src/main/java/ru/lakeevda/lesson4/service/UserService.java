package ru.lakeevda.lesson4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson4.model.User;
import ru.lakeevda.lesson4.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean updateUser(User user) {
        return userRepository.update(user);
    }

    public boolean deleteById(int id) {
        return userRepository.deleteById(id);
    }
}
