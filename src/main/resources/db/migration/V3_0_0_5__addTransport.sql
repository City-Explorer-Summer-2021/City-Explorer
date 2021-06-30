ALTER TABLE transport
DROP COLUMN name;

INSERT INTO transport (price, category_id, city_id)
VALUES
       (30.0, 2, 1),
       (30.0, 3, 1);
