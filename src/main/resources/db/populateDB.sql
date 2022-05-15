DELETE
FROM users;
DELETE
FROM RESTAURANTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANTS (NAME, DISHES, UPDATING_DATE_TIME)
VALUES ('Shaurma', 'shaurma=180;coffee=60.00', now()),
       ('Fresh Apple Lunches', 'apple=23.0;orange=49;mango=169.45', '2022-04-28 18:30:00'),
       ('Papa Grill', 'Chicken grill=299.90;Cow grill=349.90', '2022-05-01 14:58:00'),
       ('Funny Fish', 'Fish stake=310.9;Sushi=500.20', now());

INSERT INTO users (NAME, EMAIL, PASSWORD, VOTED_FOR, VOTING_DATE_TIME)
VALUES ('User', 'user@yandex.ru', '{noop}password', 100003, '2022-04-28 22:36:00'),
       ('Admin', 'admin@gmail.com', '{noop}admin', 100002, now()),
       ('Annet', 'gmail@gmail.com', '{noop}pass', 100001, '2022-04-29 11:00:00'),
       ('Pirouette', 'mao@mao.ru', '{noop}smoke', 100000, '2022-05-01 15:00:00'),
       ('Man whithout vote', 'man@without.vote', '{noop}', null, null);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100004),
       ('ADMIN', 100005),
       ('USER', 100005);