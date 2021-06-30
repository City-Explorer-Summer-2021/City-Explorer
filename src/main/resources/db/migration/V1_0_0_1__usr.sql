CREATE TABLE user_role (
    user_id INT8 NOT NULL,
    roles VARCHAR(255)
);

CREATE TABLE usr (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    active BOOLEAN NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_fk
    FOREIGN KEY (user_id) REFERENCES usr;

WITH row AS (
INSERT INTO usr (username, password, active)
VALUES ('admin', '1', true)
    RETURNING id)
INSERT INTO user_role (user_id, roles)
SELECT id, 'USER' FROM row
UNION ALL
SELECT id, 'ADMIN' FROM row;
