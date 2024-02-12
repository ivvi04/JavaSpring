package ru.lakeevda.lesson5.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.lesson5.entity.User;
import ru.lakeevda.lesson5.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.add(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(User user) {
        User updateUser = userService.update(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
