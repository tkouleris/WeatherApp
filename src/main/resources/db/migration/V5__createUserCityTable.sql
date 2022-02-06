CREATE TABLE user_city
(
    id          bigint unsigned auto_increment primary key,
    user_id     bigint unsigned not null,
    city_id     bigint unsigned not null
);

INSERT INTO user_city (user_id, city_id)VALUES(1,19583);
INSERT INTO user_city (user_id, city_id)VALUES(1,2656);