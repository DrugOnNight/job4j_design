SELECT avg(price) FROM devices;

SELECT p.name, avg(d.price)
FROM devices as d
JOIN devices_people as dp
ON dp.device_id = d.id
JOIN people as p
ON p.id = dp.people_id
GROUP BY p.name;

SELECT p.name, avg(d.price)
FROM devices as d
JOIN devices_people as dp
ON dp.device_id = d.id
JOIN people as p
ON p.id = dp.people_id
GROUP BY p.name
HAVING avg(d.price) > 500;