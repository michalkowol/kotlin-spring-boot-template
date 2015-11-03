create table cities_with_json
(
  id serial unique primary key,
  name varchar(255) not null,
  data json not null
);