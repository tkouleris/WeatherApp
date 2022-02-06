create table user_city
(
    id      bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    city_id bigint unsigned not null,
    constraint user_city_pk unique (user_id, city_id)
);

INSERT INTO user_city (user_id, city_id)VALUES(1,19583);
INSERT INTO user_city (user_id, city_id)VALUES(1,2656);