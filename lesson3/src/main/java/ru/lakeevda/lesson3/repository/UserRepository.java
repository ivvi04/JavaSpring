package ru.lakeevda.lesson3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.lakeevda.lesson3.entity.User;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> getUserRowMapper() {
        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            return user;
        };
        return userRowMapper;
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM userTable";
        return jdbcTemplate.query(sql, getUserRowMapper());
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getUserRowMapper(), id);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (name,age,email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail());
        return user;
    }

    public void update(User user) {
        String sql = "UPDATE userTable SET name = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail(), user.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
