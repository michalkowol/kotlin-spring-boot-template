create table cities
(
  id serial unique primary key,
  name varchar(255) not null
);

create table addresses
(
  id serial primary key,
  street varchar(255) not null,
  city_id int not null,
  foreign key(city_id) references cities(id)
);

create table people
(
  id serial primary key,
  name varchar(255) not null,
  age int not null
);

create table addresses_people
(
  id serial primary key,
  person_id int not null,
  address_id int not null,
  foreign key(person_id) references people(id),
  foreign key(address_id) references addresses(id)
);