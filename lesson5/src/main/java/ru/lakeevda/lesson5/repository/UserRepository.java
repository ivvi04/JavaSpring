package ru.lakeevda.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.lesson5.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
