INSERT INTO devices(name, price) values('Iphone 5', 500);
INSERT INTO devices(name, price) values('Remdi note 9', 350);
INSERT INTO devices(name, price) values('Honor 10', 400);
INSERT INTO devices(name, price) values('Iphone 11', 700);

INSERT INTO people(name) values('Oleg');
INSERT INTO people(name) values('Ivan');
INSERT INTO people(name) values('Petr');
INSERT INTO people(name) values('Egor');
INSERT INTO people(name) values('Alexandr');

INSERT INTO devices_people(device_id, people_id) values(1, 1);
INSERT INTO devices_people(device_id, people_id) values(1, 2);
INSERT INTO devices_people(device_id, people_id) values(2, 1);
INSERT INTO devices_people(device_id, people_id) values(3, 2);
INSERT INTO devices_people(device_id, people_id) values(3, 3);
INSERT INTO devices_people(device_id, people_id) values(3, 4);
INSERT INTO devices_people(device_id, people_id) values(4, 1);
INSERT INTO devices_people(device_id, people_id) values(4, 5);