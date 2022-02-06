CREATE TABLE city
(
    id          bigint unsigned auto_increment primary key,
    city_name   varchar(255)    not null,
    city_state  varchar(255)    not null,
    country     varchar(255)    not null,
    owm_id      bigint unsigned null
);

CREATE INDEX city_owm_id_index ON city (owm_id);