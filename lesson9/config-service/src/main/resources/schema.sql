CREATE TABLE IF NOT EXISTS account (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    number    BIGINT NOT NULL UNIQUE,
    balance   DECIMAL(12, 2)
);

INSERT INTO account (number, balance)
VALUES (1, 100000),
       (2, 50000),
       (3, 0);

CREATE TABLE IF NOT EXISTS product (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price    DECIMAL(12, 2) CHECK ( price > 0 ),
    amount   INT CHECK ( amount > -1 ),
    reserved INT CHECK ( reserved <= product.amount )
);

INSERT INTO product (name, price, amount, reserved)
VALUES ('iphone 12', 89000.00, 10, 0),
       ('Samsung S22+', 10000.00, 10, 0);