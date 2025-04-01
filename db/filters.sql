create table type
(
	id serial primary key,
	name      varchar(255)
);

create table product
(
	id           serial primary key,
	name         varchar(255),
	price        float,
	expired_date date,
	type_id      int references type(id)
);

insert into type(name) values
('Сыр'),
('Молоко'),
('Хлеб'),
('Фрукты'),
('Овощи'),
('Мясо'),
('Рыба'),
('Напитки'),
('Кондитерские изделия'),
('Крупы'),
('Мороженое');

insert into product(name, price, expired_date, type_id) values
('Гауда', 450.00, '2025-06-01', 1),
('Моцарелла', 500.00, '2025-05-15', 1),
('Молоко 3.2%', 120.00, '2025-04-15', 2),
('Батон Нарезной', 55.00, '2025-04-05', 3),
('Яблоки', 150.00, '2025-04-20', 4),
('Огурцы', 200.00, '2025-04-18', 5),
('Говядина', 1500.00, '2025-04-10', 6),
('Лосось', 1500.00, '2025-04-12', 7),
('Кола 1Л', 120.00, '2025-01-01', 8),
('Шоколад', 250.00, '2025-02-15', 9),
('Рис', 180.00, '2025-12-31', 10),
('Мороженое Ванильное', 180.00, '2025-07-01', 11),
('Мороженое Шоколадное', 200.00, '2025-07-10', 11),
('Мороженое Фруктовый Лед', 150.00, '2025-06-20', 11),
('Чеддер', 500.00, '2025-05-15', 1),
('Пармезан', 750.00, '2025-07-20', 1),
('Рокфор', 800.00, '2025-08-15', 1),
('Фета', 600.00, '2025-05-25', 1),
('Бри', 700.00, '2025-06-30', 1),
('Камамбер', 850.00, '2025-09-10', 1),
('Эдам', 400.00, '2025-07-05', 1),
('Маскарпоне', 550.00, '2025-06-20', 1);

select p.name "Продукт", t.name "Тип"
from product p 
join type t on p.type_id = t.id
where upper(t.name) = 'СЫР'
order by t.name asc;

select name "Продукт"
from product
where lower(name) like '%мороженое%'
order by name asc;

select expired_date as "Дата истечения срока годности"
from product
where expired_date < current_date
order by name asc;

select t.name "Тип", p.name "Продукт", p.price as "Максимальная цена"
from product p
join type t on p.type_id = t.id
where p.price = (select max(price) from product)
order by t.name asc;

select t.name "Тип", count(t.name) as "Кол-во продукции"
from product p
join type t on p.type_id = t.id
group by t.name
order by t.name asc;

select p.name "Продукт", t.name "Тип"
from product p
join type t on p.type_id = t.id
where upper(t.name) = 'СЫР' or upper(t.name) = 'МОЛОКО'
order by t.name asc;

select t.name "Тип", count(t.name) as "Кол-во продукции"
from product p
join type t on p.type_id = t.id
group by t.name
having count(t.name) < 10
order by t.name asc;

select p.name "Продукт", t.name "Тип"
from product p 
join type t on p.type_id = t.id
order by t.name asc;