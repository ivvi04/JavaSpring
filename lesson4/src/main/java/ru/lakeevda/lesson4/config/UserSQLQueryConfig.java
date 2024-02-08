package ru.lakeevda.lesson4.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "users")
@Data
public class UserSQLQueryConfig {
    private String selectUsers;
    private String selectUserById;
    private String insert;
    private String delete;
    private String update;
}
