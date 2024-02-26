package ru.lakeevda.lesson8.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_project")
public class UserProject extends EntityWithRelation {

    @Column(name = "project_id", nullable = false)
    private Long projectId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
}
