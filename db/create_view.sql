create view inactive_orders_after_2025_01_01 as
select 
    s.name as "Имя студента",
    o.active as "Статус",
    a.name as "Автор",
    b.name as "Книга",
	max(o.order_date) as "Дата последнего заказа"
from orders o
join students s on o.student_id = s.id
join books b on o.book_id = b.id
join authors a on b.author_id = a.id
group by s.name, o.active, a.name, b.name
having o.active and max(o.order_date) > '2025-01-01'
order by s.name asc, a.name asc, b.name asc;