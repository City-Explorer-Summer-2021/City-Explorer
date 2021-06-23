TRUNCATE user_role;
TRUNCATE usr CASCADE;

ALTER TABLE usr
    ADD COLUMN phone varchar(255) NOT NULL UNIQUE,
    ADD COLUMN firstname varchar(255) NOT NULL,
    ADD COLUMN lastname varchar(255) NOT NULL;

INSERT INTO usr (id, username, password, active, phone, firstname, lastname)
VALUES (1, 'admin', '1', true, '89991234567', 'ADMIN', 'ADMIN');

INSERT INTO user_role (user_id, roles)
VALUES (1, 'USER'), (1, 'ADMIN');

UPDATE usr
SET password = '$2a$08$9fpPBFoZmeUVyHP0SI52y.KZ491V9KSXyiQ2MWb2tjHMPxI5qC.hS'
WHERE id = 1;