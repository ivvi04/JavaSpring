package ru.lakeevda.springintregration.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.lakeevda.springintregration.enums.UserRole;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private UserRole role;
}
