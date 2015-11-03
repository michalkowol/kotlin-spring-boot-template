create table posts
(
  id serial unique primary key,
  title varchar(255) not null,
  body varchar(25500) not null
);