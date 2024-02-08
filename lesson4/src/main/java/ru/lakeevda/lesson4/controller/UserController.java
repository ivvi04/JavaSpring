package ru.lakeevda.lesson4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lakeevda.lesson4.model.User;
import ru.lakeevda.lesson4.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public String getUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        log.info("Get all users");
        return "user/list";
    }

    @GetMapping("/create")
    public String createUserForm(User user){
        return "user/create";
    }

    @PostMapping("/create")
    public String createUser(User user){
        User user1 = userService.saveUser(user);
        log.info("User create");
        return "redirect:/user/all";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(User user){
        userService.updateUser(user);
        log.info("User update");
        return "redirect:/user/all";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        log.info("User delete");
        return "redirect:/user/all";
    }
}
