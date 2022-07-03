CREATE TABLE car_bodies(
    id serial primary key,
    name text
);
CREATE TABLE car_engines(
    id serial primary key,
    name text
);
CREATE TABLE car_transmissions(
    id serial primary key,
    name text
);
CREATE TABLE cars(
    id serial primary key,
    name text,
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);