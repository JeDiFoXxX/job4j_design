create table people
(
	id serial primary key,
	name      varchar(255),
	surname   varchar(255)
);

create table cinema_seat
(
	id serial   primary key,
	row         int,
	seat_number int,
	people_id   int references people(id) unique
);

insert into people(name, surname) values 
('Иван', 'Иванов'),
('Петр', 'Петров'),
('Алексей', 'Сидоров');

insert into cinema_seat(row, seat_number, people_id) values 
(2, 10, 1),
(2, 11, 2),
(2, 12, 3);

select p.name, p.surname, cs.seat_number 
from people as p join cinema_seat as cs on cs.people_id = p.id;

select p.surname, cs.row, cs.seat_number
from people p join cinema_seat cs on cs.people_id = p.id;

select p.name "Имя", p.surname "Фамилия", cs.row "Ряд", cs.seat_number "Место"
from people p join cinema_seat cs on cs.people_id = p.id;