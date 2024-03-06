package ru.lakeevda.springintregration.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.lakeevda.springintregration.enums.ProjectStatus;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "project")
public class Project {
    //Поля проекта
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    @Column(nullable = false)
    private ProjectStatus status;

}