CREATE TABLE IF NOT EXISTS userTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    age integer NOT NULL
    );

INSERT INTO userTable (name, age) VALUES ('Denis', 34), ('Anna', 32);