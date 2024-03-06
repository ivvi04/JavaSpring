package ru.lakeevda.springintregration.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.springintregration.entity.Project;
import ru.lakeevda.springintregration.repository.ProjectRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(null);
    }

    public Project add(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        return projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
