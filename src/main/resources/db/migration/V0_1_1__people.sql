CREATE TABLE cities
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE addresses
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    street  VARCHAR(255) NOT NULL,
    city_id UUID         NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

CREATE TABLE people
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL
);

CREATE TABLE addresses_people
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id  UUID NOT NULL,
    address_id UUID NOT NULL,
    FOREIGN KEY (person_id) REFERENCES people (id),
    FOREIGN KEY (address_id) REFERENCES addresses (id)
);
