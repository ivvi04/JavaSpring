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
    email VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_project (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    related_entity_id LONG NOT NULL,
    project_id LONG NOT NULL,
    user_id LONG NOT NULL
);