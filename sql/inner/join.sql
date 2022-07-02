SELECT * FROM fauna as f INNER
JOIN discoverers as d
ON f.discoverer_id = d.id;

SELECT f.name, d.name FROM fauna as f
JOIN discoverers as d
ON f.discoverer_id = d.id;

SELECT f.name as Рыба, d.name as Открыватель
FROM fauna as f
JOIN discoverers as d
ON f.discoverer_id = d.id;