CREATE TABLE publishing_houses(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE books(
    id serial primary key,
    title varchar(255),
    publishing_house_id int references publishing_houses(id)
);