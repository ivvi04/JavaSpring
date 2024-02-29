CREATE TABLE IF NOT EXISTS account (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    balance   DECIMAL(12, 2)
);

INSERT INTO account (name, balance)
VALUES ('Счет1', 100000),
       ('Счет2', 50000),
       ('Счет3', 0);

CREATE TABLE IF NOT EXISTS product (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price    DECIMAL(12, 2) CHECK ( price > 0 ),
    amount   INT CHECK ( amount > -1 ),
    reserved INT CHECK ( reserved <= product.amount ),
    image    IMAGE
);

INSERT INTO product (name, price, amount, reserved, image)
VALUES ('Ноутбук', 89000.00, 10, 0, FILE_READ('classpath:/static/1.jpg')),
       ('Телефон', 23000.00, 20, 0, FILE_READ('classpath:/static/2.jpg')),
       ('Стиральная машина', 33000.00, 8, 0, FILE_READ('classpath:/static/3.jpg')),
       ('Холодильник', 24000.00, 4, 0, FILE_READ('classpath:/static/4.jpg')),
       ('Варочная панель', 15000.00, 7, 0, FILE_READ('classpath:/static/5.jpg')),
       ('Духовой шкаф', 42000.00, 5, 0, FILE_READ('classpath:/static/6.jpg'));

