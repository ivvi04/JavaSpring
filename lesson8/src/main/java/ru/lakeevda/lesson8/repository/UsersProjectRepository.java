package ru.lakeevda.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.lesson8.entity.UserProject;

import java.util.List;

public interface UsersProjectRepository extends JpaRepository<UserProject, Long> {
    List<UserProject> findAllByProjectId(Long projectId);
    List<UserProject> findAllByUserId(Long userId);
    UserProject findByUserIdAndProjectId(Long userId, Long projectId);
}
