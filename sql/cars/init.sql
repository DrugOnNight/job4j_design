INSERT INTO car_bodies(name) values('седан');
INSERT INTO car_bodies(name) values('хэтчбек');
INSERT INTO car_bodies(name) values('пикап');
INSERT INTO car_bodies(name) values('кроссовер');

INSERT INTO car_engines(name) values('2JZ');
INSERT INTO car_engines(name) values('1AZ-FE');
INSERT INTO car_engines(name) values('B38A');
INSERT INTO car_engines(name) values('6.7 TD');

INSERT INTO car_transmissions(name) values('Ручная');
INSERT INTO car_transmissions(name) values('Автомат');
INSERT INTO car_transmissions(name) values('Вариатор');
INSERT INTO car_transmissions(name) values('Роботизированная');

INSERT INTO cars(name,  body_id, engine_id, transmission_id)
values('Марк 2', 1, 1, 1);
INSERT INTO cars(name,  body_id, engine_id, transmission_id)
values('Рав 4', 4, 2, 2);
INSERT INTO cars(name,  body_id, engine_id, transmission_id)
values('БМВ 116i', 2, 3, 4);



