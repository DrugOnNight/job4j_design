create table discoverers(
    id serial primary key,
    name text
);
create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date,
    discoverer_id int references discoverers(id)
);