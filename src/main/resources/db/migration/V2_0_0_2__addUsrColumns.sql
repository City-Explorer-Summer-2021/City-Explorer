ALTER TABLE usr
    ADD COLUMN phone varchar(255),
    ADD COLUMN firstname varchar(255),
    ADD COLUMN lastname varchar(255);

UPDATE usr
SET phone= '89990000000', firstname='System', lastname='Record';

ALTER TABLE usr
    ALTER COLUMN phone SET NOT NULL,
    ALTER COLUMN firstname SET NOT NULL,
    ALTER COLUMN lastname SET NOT NULL;

ALTER TABLE usr
    ADD CONSTRAINT phone_unique UNIQUE (phone);
