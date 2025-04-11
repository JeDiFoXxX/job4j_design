create table orders 
(
    id serial primary key,
    customer_name varchar(255),
    item text,
    quantity int
);

insert into orders (customer_name, item, quantity) values
('Алиса', 'Ноутбук', 1),
('Боб', 'Телефон', 2),
('Чарли', 'Планшет', 3);