package ru.lakeevda.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.lesson5.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
