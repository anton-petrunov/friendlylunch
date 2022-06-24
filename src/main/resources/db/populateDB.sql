DELETE
FROM users;
DELETE
FROM RESTAURANTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANTS (NAME)
VALUES ('Shaurma Number ONE'),
       ('Fresh Apple Lunches'),
       ('Papa Grill'),
       ('Funny Fish');

INSERT INTO users (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Annet', 'gmail@gmail.com', '{noop}pass'),
       ('Pirouette', 'mao@mao.ru', '{noop}smoke'),
       ('Man whithout vote', 'man@without.vote', '{noop}');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100004),
       ('ADMIN', 100005),
       ('USER', 100005);