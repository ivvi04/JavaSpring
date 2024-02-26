package ru.lakeevda.lesson8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson8.aspect.TrackUserAction;
import ru.lakeevda.lesson8.entity.Project;
import ru.lakeevda.lesson8.repository.ProjectRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @TrackUserAction
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @TrackUserAction
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(null);
    }

    @TrackUserAction
    public Project add(Project project) {
        return projectRepository.save(project);
    }

    @TrackUserAction
    public Project update(Project project) {
        return projectRepository.save(project);
    }

    @TrackUserAction
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
