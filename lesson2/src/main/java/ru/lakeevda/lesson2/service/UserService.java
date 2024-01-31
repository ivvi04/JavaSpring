package ru.lakeevda.lesson2.service;

import org.springframework.stereotype.Service;
import ru.lakeevda.lesson2.model.User;
import ru.lakeevda.lesson2.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.getUserById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.update(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
