--Выполнить запросы с left, right, full, cross соединениями
--left
SELECT e.name, d.name
FROM employees e
LEFT JOIN departments d
ON e.department_id = d.id;
--right
SELECT e.name, d.name
FROM employees e
RIGHT JOIN departments d
ON e.department_id = d.id;
--full
SELECT e.name, d.name
FROM employees e
FULL JOIN departments d
ON e.department_id = d.id;
--cross
SELECT e.name, d.name
FROM employees e
CROSS JOIN departments d;
--3. Используя left join найти департаменты, у которых нет работников
SELECT d.name
FROM departments d
LEFT JOIN employees e
ON e.department_id = d.id
GROUP BY d.name
HAVING count(e.name) = 0;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат
--(порядок вывода колонок в эти запросах также должен быть идентичный).
SELECT e.id, e.name, d.id, d.name
FROM employees e
LEFT JOIN departments d
ON e.department_id = d.id;

SELECT e.id, e.name, d.id, d.name
FROM departments d
RIGHT JOIN employees e
ON e.department_id = d.id;