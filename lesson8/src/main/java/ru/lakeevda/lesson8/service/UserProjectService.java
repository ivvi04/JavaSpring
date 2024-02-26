package ru.lakeevda.lesson8.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakeevda.lesson8.aspect.TrackUserAction;
import ru.lakeevda.lesson8.entity.Project;
import ru.lakeevda.lesson8.entity.User;
import ru.lakeevda.lesson8.entity.UserProject;
import ru.lakeevda.lesson8.repository.ProjectRepository;
import ru.lakeevda.lesson8.repository.UserRepository;
import ru.lakeevda.lesson8.repository.UsersProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UsersProjectRepository usersProjectRepository;

    @TrackUserAction
    public List<User> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findAllByProjectId(projectId)
                .stream()
                .map(usersProject -> userRepository.findById(usersProject.getUserId()).orElse(null))
                .collect(Collectors.toList());
    }

    @TrackUserAction
    public List<Project> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findAllByUserId(userId)
                .stream()
                .map(usersProject -> projectRepository.findById(usersProject.getProjectId()).orElse(null))
                .collect(Collectors.toList());
    }

    @TrackUserAction
    public void addUserToProject(Long userId, Long projectId) {
        UserProject userProject = new UserProject();
        userProject.setRelatedEntityId(1L);
        userProject.setProjectId(projectId);
        userProject.setUserId(userId);
        usersProjectRepository.save(userProject);
    }

    @TrackUserAction
    public void removeUserFromProject(Long userId, Long projectId) {
        UserProject usersProject = usersProjectRepository.findByUserIdAndProjectId(userId, projectId);
        if (usersProject != null) usersProjectRepository.delete(usersProject);
    }

}
