create table students(
	id serial primary key,
	name text,
	birthday date,
	point real
);

insert into students(name, birthday, point) values('A','2002-05-05','99.9');
insert into students(name, birthday, point) values('B','2002-10-10','89.9');

delete from students;

