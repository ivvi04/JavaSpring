package ru.lakeevda.lesson4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson4.model.User;
import ru.lakeevda.lesson4.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.getUsers();
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
