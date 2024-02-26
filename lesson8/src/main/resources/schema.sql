CREATE TABLE IF NOT EXISTS users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    role VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS project (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    created_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_project (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    related_entity_id LONG NOT NULL,
    project_id LONG NOT NULL,
    user_id LONG NOT NULL
);

INSERT INTO users (name, password, email, role) VALUES ('Денис', '123', 'denis@mail.ru', 'DEVELOPER');
INSERT INTO users (name, password, email, role) VALUES ('Анна', '1234', 'anna@mail.ru', 'MANAGER');
INSERT INTO users (name, password, email, role) VALUES ('Андрей', '12345', 'andrej@mail.ru', 'TESTER');

INSERT INTO project (name, description, created_date, status) VALUES ('Проект 1', 'Проект один', PARSEDATETIME('2024-01-30', 'yyyy-MM-dd'), 'NOT_STARTED');
INSERT INTO project (name, description, created_date, status) VALUES ('Проект 2', 'Проект два', PARSEDATETIME('2024-01-29', 'yyyy-MM-dd'), 'IN_PROGRESS');
INSERT INTO project (name, description, created_date, status) VALUES ('Проект 3', 'Проект три', PARSEDATETIME('2024-01-20', 'yyyy-MM-dd'), 'COMPLETED');

INSERT INTO user_project (related_entity_id, project_id, user_id) VALUES (1, 1, 2);
INSERT INTO user_project (related_entity_id, project_id, user_id) VALUES (1, 2, 3);
INSERT INTO user_project (related_entity_id, project_id, user_id) VALUES (1, 3, 1);