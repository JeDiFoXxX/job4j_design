drop table customers, orders;
create table customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

create table orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into customers (first_name, last_name, age, country) values
('Иван', 'Петров', 30, 'Россия'),
('Мария', 'Иванова', 25, 'Россия'),
('Алексей', 'Сидоров', 40, 'Казахстан'),
('Ольга', 'Кузнецова', 35, 'Беларусь'),
('Дмитрий', 'Новиков', 28, 'Россия'),
('Екатерина', 'Смирнова', 25, 'Украина');

insert into orders (amount, customer_id) values
(500, 1),
(300, 2),
(700, 3),
(400, 5),
(150, 1),
(350, 2);

select * from customers where age = (select min(age) from customers);

select first_name "Имя", last_name "Фамилия", age "Возраст", country "Страна" 
from customers where id not in (select customer_id from orders);