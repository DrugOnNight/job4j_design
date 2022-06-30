CREATE TABLE roles(
    id serial primary key,
    role varchar(255)
);
CREATE TABLE rules(
    id serial primary key,
    rule varchar(255)
);
CREATE TABLE role_rule(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);
CREATE TABLE users(
    id serial primary key,
    name varchar(255),
    role_id int references roles(id)
);
CREATE TABLE category(
    id serial primary key,
    title varchar(255)
);
CREATE TABLE state(
    id serial primary key,
    name varchar(255)
);
CREATE TABLE items(
    id serial primary key,
    name varchar(255),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);
CREATE TABLE comments(
    id serial primary key,
    comment text,
    item_id int references items(id)
);
CREATE TABLE attachs(
    id serial primary key,
    link varchar(255),
    item_id int references items(id)
);