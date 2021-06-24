ALTER TABLE usr
    ADD COLUMN phone varchar(255),
    ADD COLUMN firstname varchar(255),
    ADD COLUMN lastname varchar(255);

UPDATE usr
SET phone= '89991234567', firstname='ADMIN', lastname='ADMIN'
WHERE id = 1;

ALTER TABLE usr
    ALTER COLUMN phone SET NOT NULL,
    ALTER COLUMN firstname SET NOT NULL,
    ALTER COLUMN lastname SET NOT NULL;

ALTER TABLE usr
    ADD CONSTRAINT phone_unique UNIQUE (phone);
