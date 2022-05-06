DROP TABLE users IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE restaurants
(
    id                 INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    dishes             VARCHAR(2000),
    updating_date_time TIMESTAMP

);
CREATE UNIQUE INDEX restaurants_unique_id_name_idx
    ON restaurants (id, name);

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    voted_for        INTEGER,
    voting_date_time TIMESTAMP,
    CONSTRAINT id_email_idx UNIQUE (id, email),
    FOREIGN KEY (voted_for) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email)