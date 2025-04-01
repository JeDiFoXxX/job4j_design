create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values 
('Утюг', 3500),
('Холодильник', 45000),
('Смартфон', 25000),
('Ноутбук', 70000),
('Телевизор', 55000);

insert into people(name) values
('Алексей'),
('Мария'),
('Иван'),
('Екатерина'),
('Дмитрий');

insert into devices_people(device_id, people_id) values
(1, 1),
(2, 2),
(3, 2),
(3, 3),
(5, 3),
(4, 4),
(5, 4);

select avg(price) as "Средняя цена всех устройств" from devices;

select p.name "Имя", avg(d.price) as "Средняя цена устройств"
from devices_people dp 
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000
order by p.name asc;