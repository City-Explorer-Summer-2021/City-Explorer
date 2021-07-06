ALTER TABLE hotel
ADD COLUMN price_from NUMERIC(15, 2),
ADD COLUMN price_to NUMERIC(15, 2);

UPDATE hotel
SET price_from = 0,
    price_to = 0;

ALTER TABLE hotel
    ALTER column price_from SET NOT NULL,
    ALTER column price_to SET NOT NULL;