package ru.lakeevda.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.lesson8.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
