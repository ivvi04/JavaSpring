package ru.lakeevda.lesson4.repository;

import lombok.RequiredArgsConstructor;
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
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
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
        jdbcTemplate.update(userSQLQueryConfig.getInsert(), user.getName(), user.getAge());
        return user;
    }

    public boolean update(User user) {
        return jdbcTemplate.update(userSQLQueryConfig.getUpdate(), user.getName(), user.getAge(), user.getId()) == 1;
    }

    public boolean deleteById(int id) {
        return jdbcTemplate.update(userSQLQueryConfig.getDelete(), id) == 1;
    }
}
