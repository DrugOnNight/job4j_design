CREATE TABLE authors(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE books(
    id serial primary key,
    title varchar(255)
);

CREATE TABLE author_book(
    id serial primary key,
    author_id int references authors(id),
    book_id int references books(id)
);