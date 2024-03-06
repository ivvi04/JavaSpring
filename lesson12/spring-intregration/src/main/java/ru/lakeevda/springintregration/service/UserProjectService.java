package ru.lakeevda.springintregration.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.springintregration.entity.Project;
import ru.lakeevda.springintregration.entity.User;
import ru.lakeevda.springintregration.entity.UserProject;
import ru.lakeevda.springintregration.repository.ProjectRepository;
import ru.lakeevda.springintregration.repository.UserRepository;
import ru.lakeevda.springintregration.repository.UsersProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UsersProjectRepository usersProjectRepository;

    public List<User> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findAllByProjectId(projectId)
                .stream()
                .map(usersProject -> userRepository.findById(usersProject.getUserId()).orElse(null))
                .collect(Collectors.toList());
    }

    public List<Project> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findAllByUserId(userId)
                .stream()
                .map(usersProject -> projectRepository.findById(usersProject.getProjectId()).orElse(null))
                .collect(Collectors.toList());
    }

    public void addUserToProject(Long userId, Long projectId) {
        UserProject userProject = new UserProject();
        userProject.setRelatedEntityId(1L);
        userProject.setProjectId(projectId);
        userProject.setUserId(userId);
        usersProjectRepository.save(userProject);
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        UserProject usersProject = usersProjectRepository.findByUserIdAndProjectId(userId, projectId);
        if (usersProject != null) usersProjectRepository.delete(usersProject);
    }

}
