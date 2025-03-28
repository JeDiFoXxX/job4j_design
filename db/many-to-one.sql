create table customer(
	id serial primary key,
    name varchar(255)
);

create table orders(
	id serial primary key,
    order_number bigint,
	customer_id int references customer(id)
);

insert into customer(name) values ('Jojo');
insert into orders(order_number, customer_id) values (1234567, 1);

select * from orders;