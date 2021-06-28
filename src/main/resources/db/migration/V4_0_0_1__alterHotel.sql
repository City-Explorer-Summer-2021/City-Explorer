ALTER TABLE hotel
    ADD COLUMN user_id BIGINT;

UPDATE hotel
SET user_id = 1;

ALTER TABLE  hotel
    ALTER COLUMN user_id SET NOT NULL;
