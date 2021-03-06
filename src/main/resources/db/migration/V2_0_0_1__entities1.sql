CREATE TABLE city (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    lat DECIMAL(9,6) NOT NULL,
    lon DECIMAL(9,6) NOT NULL
);

CREATE TABLE city_photo (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE transport_category (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE transport (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price numeric(19, 2) NOT NULL,
    category_id BIGINT NOT NULL,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES transport_category(id),
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE attraction (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL UNIQUE,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE attraction_photo (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    attraction_id BIGINT NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id)
);

CREATE TABLE event (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    finish_date TIMESTAMP NOT NULL,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE event_photo (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    event_id BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id)
);

CREATE TABLE food_place (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city_id BIGINT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);
