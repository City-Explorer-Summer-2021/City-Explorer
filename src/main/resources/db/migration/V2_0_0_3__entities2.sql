CREATE TABLE hotel_category (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE hotel (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    city_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE hotel_photo (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    hotel_id BIGINT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);

CREATE TABLE food_place_valuation (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    food_place_id BIGINT NOT NULL,
    value INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usr(id),
    FOREIGN KEY (food_place_id) REFERENCES food_place(id),
    CONSTRAINT food_place_valuation_value_check CHECK (value >= 1 AND value <= 5)
)
