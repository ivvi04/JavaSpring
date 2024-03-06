package ru.lakeevda.springintregration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.springintregration.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
