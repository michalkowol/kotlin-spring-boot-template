CREATE TABLE products (
    id               SERIAL UNIQUE PRIMARY KEY,
    name             TEXT,
    price            NUMERIC,
    CHECK (price > 0),
    discounted_price NUMERIC,
    CHECK (discounted_price > 0),
    CHECK (price > discounted_price)
);

CREATE TABLE mpaa_film_ratings
(
    id   SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE genres
(
    id   SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE movies
(
    id                SERIAL UNIQUE PRIMARY KEY,
    title             VARCHAR(255) NOT NULL,
    mpaa_film_rate_id INT          NOT NULL,
    genre_id          INT          NOT NULL,
    FOREIGN KEY (mpaa_film_rate_id) REFERENCES mpaa_film_ratings (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE VIEW movies_flatten AS
    SELECT
        movies.id,
        movies.title,
        genres.name            AS genre,
        mpaa_film_ratings.name AS mpaa_film_rate
    FROM movies
        JOIN genres ON movies.genre_id = genres.id
        JOIN mpaa_film_ratings ON movies.mpaa_film_rate_id = mpaa_film_ratings.id;

CREATE OR REPLACE FUNCTION insert_movie(title VARCHAR(255), genre VARCHAR(255), mpaa_film_rate VARCHAR(255))
    RETURNS INT AS $$
DECLARE
    g_id INT;
    r_id INT;
    m_id INT;
BEGIN
    BEGIN
        INSERT INTO genres AS g (name) VALUES (genre)
        ON CONFLICT (name)
            DO UPDATE SET id = g.id
        RETURNING id
            INTO g_id;
        INSERT INTO mpaa_film_ratings AS r (name) VALUES (mpaa_film_rate)
        ON CONFLICT (name)
            DO UPDATE SET id = r.id
        RETURNING id
            INTO r_id;
        INSERT INTO movies (title, mpaa_film_rate_id, genre_id) VALUES (title, r_id, g_id)
        RETURNING id
            INTO m_id;
        RETURN m_id;
    END;
END;
$$ LANGUAGE plpgsql;
