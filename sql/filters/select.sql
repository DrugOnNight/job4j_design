--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product as p
JOIN type as t
ON t.id = p.type_id
WHERE t.name LIKE 'Сыр';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * FROM product
WHERE name LIKE '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT * FROM product as p
JOIN type as t
ON t.id = p.type_id
WHERE p.expired_date < current_date;
--4. Написать запрос, который выводит самый дорогой продукт.
SELECT *
FROM product
WHERE price = (
    SELECT max(price)
    FROM product
);
--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
SELECT t.name, count(*)
FROM type as t
JOIN product as p
ON p.type_id = t.id
GROUP BY t.name;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product as p
JOIN type as t
ON t.id = p.type_id
WHERE t.name LIKE 'Сыр' OR t.name LIKE 'Молоко';
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name, count(*)
FROM type as t
JOIN product as p
ON p.type_id = t.id
GROUP BY t.name
HAVING count(*) < 10;
--8. Вывести все продукты и их тип.
SELECT p.name, t.name
FROM type as t
JOIN product as p
ON p.type_id = t.id;

