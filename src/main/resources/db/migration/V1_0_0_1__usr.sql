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

INSERT INTO usr (id, username, password, active)
VALUES (1, 'admin', '1', true);

INSERT INTO user_role (user_id, roles)
VALUES (1, 'USER'), (1, 'ADMIN');
