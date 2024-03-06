package ru.lakeevda.springintregration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.springintregration.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
