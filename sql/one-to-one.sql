CREATE TABLE author(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE phone(
    id serial primary key,
    phone text,
);

CREATE TABLE author_phone(
    id serial primary key,
    author_id int references author(id) unique,
    phone_id int references phone(id) unique
);