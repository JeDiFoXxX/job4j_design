drop table products;

create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price) values
('Продукт А', 'Производитель 1', 10, 150),
('Продукт Б', 'Производитель 2', 0, 200),
('Продукт В', 'Производитель 3', 5, 300),
('Продукт Г', 'Производитель 4', 0, 250);

call delete_by_using_procedure();

select delete_by_using_function(1);

select * from products;