create table students(
	id serial primary key,
    name varchar(255)
);

create table books(
	id serial primary key,
    name varchar(255)
);

create table students_books(
	id serial primary key,
    student_id int references students(id),
	book_id int references books(id)
);

insert into students(name) values ('V');
insert into students(name) values ('Johnny Silverhand');
insert into students(name) values ('Adam Smasher');

insert into books(name) values ('The Fellowship of the Ring');  
insert into books(name) values ('The Two Towers');  
insert into books(name) values ('The Return of the King');

insert into students_books(student_id, book_id) values (1, 1);
insert into students_books(student_id, book_id) values (1, 2);
insert into students_books(student_id, book_id) values (1, 3);
insert into students_books(student_id, book_id) values (2, 1);
insert into students_books(student_id, book_id) values (2, 2);
insert into students_books(student_id, book_id) values (3, 1);

select * from students_books;