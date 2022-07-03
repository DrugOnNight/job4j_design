CREATE TABLE departments(
    id serial primary key,
    name text
);

CREATE TABLE employees(
    id serial primary key,
    name text,
    department_id int references departments(id)
);