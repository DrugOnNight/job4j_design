--create
CREATE TABLE teens(
    id serial primary key,
    name text,
    gender text
);

--init
INSERT INTO teens(name, gender) values('Oleg', 'Male');
INSERT INTO teens(name, gender) values('Ivan', 'Male');
INSERT INTO teens(name, gender) values('Anna', 'Female');
INSERT INTO teens(name, gender) values('Olga', 'Female');
INSERT INTO teens(name, gender) values('Artur', 'Male');
INSERT INTO teens(name, gender) values('Oksana', 'Female');

--cross join
SELECT t1.name, t2.name, t1.gender, t2.gender
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender;