package ru.lakeevda.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.lesson8.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
