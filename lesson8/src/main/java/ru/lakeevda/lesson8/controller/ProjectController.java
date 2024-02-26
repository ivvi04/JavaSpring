package ru.lakeevda.lesson8.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.lesson8.entity.Project;
import ru.lakeevda.lesson8.service.ProjectService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        List<Project> projectList = projectService.findAll();
        return ResponseEntity.ok(projectList);
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        project.setCreatedDate(LocalDate.now());
        Project newProject = projectService.add(project);
        return ResponseEntity.ok(newProject);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable Long id, Model model){
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Project> updateProject(Project project) {
        Project updateProject = projectService.update(project);
        return ResponseEntity.ok(updateProject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
