CREATE TABLE city
(
    id  bigint unsigned auto_increment primary key,
    city_name varchar(255) not null,
    country varchar(255) not null,
    owm_id bigint unsigned
);