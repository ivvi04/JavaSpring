package ru.lakeevda.lesson3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lakeevda.lesson3.entity.User;
import ru.lakeevda.lesson3.service.DataProcessingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService dataProcessingService;

    @GetMapping//localhost:8080/tasks
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    @GetMapping("/sort")//localhost:8080/tasks/sort
    public List<User> sortUsersByAge() {
        return dataProcessingService.sortUsersByAge(dataProcessingService.getUsers());
    }

    @GetMapping("/filter/{age}")//localhost:8080/tasks/filter/34
    public List<User> filterUsersByAge(@PathVariable int age) {
        return dataProcessingService.filterUsersByAge(dataProcessingService.getUsers(), age);
    }

    @GetMapping("/calc")//localhost:8080/tasks/calc
    public double calculateAverageAge() {
        return dataProcessingService.calculateAverageAge(dataProcessingService.getUsers());
    }
}
