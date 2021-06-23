
CREATE TABLE city
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    lat float8 NOT NULL,
    lon float8 NOT NULL
);

CREATE TABLE city_photo(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    city_id BIGSERIAL NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE transport_category(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE transport(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price VARCHAR(255) NOT NULL,
    category_id BIGSERIAL NOT NULL,
    FOREIGN KEY(category_id) REFERENCES transport_category(id)
);

CREATE TABLE attraction(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL UNIQUE,
    city_id BIGSERIAL NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE attraction_photo(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    attraction_id BIGSERIAL NOT NULL,
    FOREIGN KEY (attraction_id) REFERENCES attraction(id)
);

CREATE TABLE event(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    finish_date TIMESTAMP NOT NULL,
    city_id BIGSERIAL NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE event_photo(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    event_id BIGSERIAL NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id)
);

CREATE TABLE food_place(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1023) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city_id BIGSERIAL NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city(id)
);




