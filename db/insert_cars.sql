insert into car_bodies (name) values
('Седан'),
('Хэтчбек'),
('Купе'),
('Универсал'),
('Кабриолет');

insert into car_engines (name) values
('Электрический'),
('Бензиновый'),
('Дизельный');

insert into car_transmissions (name) values
('Механическая'),
('Автоматическая'),
('Полуавтоматическая');

insert into cars (name, body_id, engine_id, transmission_id) values
('Тойота Королла', 1, 1, 2),
('Тесла Модель 3', 2, 2, 2),
('Мерседес-Бенц C-Класс', 3, 1, 2),
('Фольксваген Пассат', 4, 2, 1),
('Рено Логан', 1, 2, 2);
