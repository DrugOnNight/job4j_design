CREATE TABLE authors(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE phones(
    id serial primary key,
    phone text,
);

CREATE TABLE author_phone(
    id serial primary key,
    author_id int references authors(id),
    phone_id int references phones(id)
);