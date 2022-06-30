CREATE TABLE authors(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE phones(
    id serial primary key,
    phone text,
    author_id int references authors(id) unique
);