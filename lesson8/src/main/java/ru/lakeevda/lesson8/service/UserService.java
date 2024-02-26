package ru.lakeevda.lesson8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson8.aspect.TrackUserAction;
import ru.lakeevda.lesson8.entity.User;
import ru.lakeevda.lesson8.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @TrackUserAction
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @TrackUserAction
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    @TrackUserAction
    public User add(User user) {
        return userRepository.save(user);
    }

    @TrackUserAction
    public User update(User user) {
        return userRepository.save(user);
    }

    @TrackUserAction
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
