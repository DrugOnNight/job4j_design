--Вывести список всех машин и все привязанные к ним детали
SELECT c.id, c.name as Название, cb.name as Тип, ce.name as Двигатель, ct.name as КП
FROM cars c
LEFT JOIN car_bodies cb
ON c.body_id = cb.id
LEFT JOIN car_engines ce
ON c.engine_id = ce.id
LEFT JOIN car_transmissions ct
ON c.transmission_id = ct.id;
--Вывести кузовы, которые не используются НИ в одной машине.
SELECT cb.name, count(c.name)
FROM cars c
RIGHT JOIN car_bodies cb
ON c.body_id = cb.id
GROUP BY cb.name
HAVING count(c.name) = 0;
--Вывести двигатели, которые не используются НИ в одной машине
SELECT ce.name, count(c.name)
FROM cars c
RIGHT JOIN car_engines ce
ON c.engine_id = ce.id
GROUP BY ce.name
HAVING count(c.name) = 0;
--Вывести коробки передач, которые не используются НИ в одной машине
SELECT ct.name, count(c.name)
FROM car_transmissions ct
LEFT JOIN cars c
ON c.transmission_id = ct.id
GROUP BY ct.name
HAVING count(c.name) = 0;