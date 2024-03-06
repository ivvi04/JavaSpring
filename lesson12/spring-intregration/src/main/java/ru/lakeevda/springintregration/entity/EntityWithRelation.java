package ru.lakeevda.springintregration.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public abstract class EntityWithRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "related_entity_id", nullable = false)
    private Long relatedEntityId;
}
