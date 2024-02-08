package ru.lakeevda.lesson4.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.lakeevda.lesson4.config.UserSQLQueryConfig;
import ru.lakeevda.lesson4.model.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserSQLQueryConfig userSQLQueryConfig;

    private RowMapper<User> getUserRowMapper() {
        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        };
        return userRowMapper;
    }

    public List<User> getUsers() {
        return jdbcTemplate.query(userSQLQueryConfig.getSelectUsers(), getUserRowMapper());
    }

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(userSQLQueryConfig.getSelectUserById(), getUserRowMapper(), id);
    }

    public User save(User user) {
        jdbcTemplate.update(userSQLQueryConfig.getInsert(), user.getFirstName(), user.getLastName());
        return user;
    }

    public void update(User user) {
        jdbcTemplate.update(userSQLQueryConfig.getUpdate(), user.getFirstName(), user.getLastName(), user.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(userSQLQueryConfig.getDelete(), id);
    }
}
