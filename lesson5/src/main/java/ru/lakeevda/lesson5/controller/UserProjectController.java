package ru.lakeevda.lesson5.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.lesson5.entity.Project;
import ru.lakeevda.lesson5.entity.User;
import ru.lakeevda.lesson5.service.UserProjectService;

import java.util.List;

@Controller
@RequestMapping("/user_project")
@AllArgsConstructor
public class UserProjectController {
    private final UserProjectService userProjectService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long projectId) {
        List<User> userList = userProjectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        List<Project> projectList = userProjectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projectList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUserToProject(@RequestParam Long userId,
                                                 @RequestParam Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> removeUserFromProject(@RequestParam Long userId,
                                                      @RequestParam Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}
