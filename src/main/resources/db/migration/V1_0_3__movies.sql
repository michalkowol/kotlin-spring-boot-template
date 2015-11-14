create table products (
  id serial unique primary key,
  name text,
  price numeric,
  check (price > 0),
  discounted_price numeric,
  check (discounted_price > 0),
  check (price > discounted_price)
);

create table mpaa_film_ratings
(
  id serial unique primary key,
  name varchar(255) unique not null
);

create table genres
(
  id serial unique primary key,
  name varchar(255) unique not null
);

create table movies
(
  id serial unique primary key,
  title varchar(255) not null,
  mpaa_film_rate_id int not null,
  genre_id int not null,
  foreign key(mpaa_film_rate_id) references mpaa_film_ratings(id),
  foreign key(genre_id) references genres(id)
);

create view movies_flatten as
  select movies.id, movies.title, genres.name as genre, mpaa_film_ratings.name as mpaa_film_rate from movies
  join genres on movies.genre_id = genres.id
  join mpaa_film_ratings on movies.mpaa_film_rate_id = mpaa_film_ratings.id;

create or replace function insert_movie(title varchar(255), genre varchar(255), mpaa_film_rate varchar(255)) returns int as $$
declare
  g_id int;
  r_id int;
  m_id int;
begin
  begin
    insert into genres as g (name) values (genre) on conflict (name) do update set id = g.id returning id into g_id;
    insert into mpaa_film_ratings as r (name) values (mpaa_film_rate) on conflict (name) do update set id = r.id returning id into r_id;
    insert into movies (title, mpaa_film_rate_id, genre_id) values (title, r_id, g_id) returning id into m_id;
    return m_id;
  end;
end;
$$ language plpgsql;