CREATE TABLE users
(
    id  bigint unsigned auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255),
    constraint users_email_unique unique (email)
);