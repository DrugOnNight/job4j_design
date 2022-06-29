CREATE TABLE books(
    id serial primary key,
    title varchar(255),
    price money,
    stock integer
);

INSERT INTO books(title, price, stock) values('Clean code', 2500, 28);

SELECT * FROM books;

UPDATE books SET stock=27;

SELECT * FROM books;

DELETE FROM books;

SELECT * FROM books;