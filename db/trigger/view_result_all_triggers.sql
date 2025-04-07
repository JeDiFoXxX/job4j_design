alter table products disable trigger calculated_tax_after_insert;
alter table products disable trigger calculated_tax_before_insert;
alter table products enable trigger copy_data_from_products_to_history_of_price;

insert into products (name, producer, count, price) values
('Ноутбук', 'Dell', 10, 120000),
('Смартфон', 'Samsung', 25, 80000),
('Наушники', 'Sony', 50, 15000);
-- select * from products;
select * from history_of_price;




