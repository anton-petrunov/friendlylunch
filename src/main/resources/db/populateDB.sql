DELETE
FROM users;
DELETE
FROM RESTAURANTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Annet', 'gmail@gmail.com', '{noop}pass'),
       ('Pirouette', 'mao@mao.ru', '{noop}smoke'),
       ('Man whithout vote', 'man@without.vote', '{noop}');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Shaurma Number ONE'),
       ('Fresh Apple Lunches'),
       ('Papa Grill'),
       ('Funny Fish');

INSERT INTO MENUS (RESTAURANT_ID, DATE)
VALUES (100005, '2022-07-25'),
       (100006, '2022-6-25'),
       (100007, now),
       (100008, now);

INSERT INTO DISHES (MENU_ID, NAME, PRICE)
VALUES (100009, 'Coca-Cola', 140),
       (100009, 'Pizza Formaggio', 490),
       (100010, 'Chief Burger', 219);

INSERT INTO VOTES (MENU_ID, USER_ID)
VALUES (100009, 100001);
