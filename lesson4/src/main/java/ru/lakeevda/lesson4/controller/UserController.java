package ru.lakeevda.lesson4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        model.addAttribute("pageTitle", "Все пользователи");
        log.info("Get all users");
        return "user/list";
    }

    @GetMapping("/create")
    public String createUserForm(User user, Model model){
        model.addAttribute("pageTitle", "Создание нового пользователя");
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
        model.addAttribute("pageTitle", "Редактор пользователя: " + user.getName());
        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes){
        if (userService.updateUser(user)) {
            log.info(String.format("User with ID: %d is been success update.", user.getId()));
            redirectAttributes.addFlashAttribute("message", "Пользователь был успешно обновлен!");
        } else {
            log.info(String.format("User with ID: %d not found", user.getId()));
            redirectAttributes.addFlashAttribute("message", "Ошибка!");
        }
        return "redirect:/user/all";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        if (userService.deleteById(id)) {
            log.info(String.format("User with ID: %d is been success delete.", id));
            redirectAttributes.addFlashAttribute("message", "Пользователь был успешно удален!");
        } else {
            log.info(String.format("User with ID: %d not found", id));
            redirectAttributes.addFlashAttribute("message", "Ошибка!");
        }
        return "redirect:/user/all";
    }
}
