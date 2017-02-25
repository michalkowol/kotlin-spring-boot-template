CREATE TABLE cities_with_json
(
    id   SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    data JSON         NOT NULL
);
