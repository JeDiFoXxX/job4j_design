create table students
(
    id   serial primary key,
    name varchar(50)
);

create table authors
(
    id   serial primary key,
    name varchar(50)
);

create table books
(
    id        serial primary key,
    name      varchar(200),
    author_id integer references authors (id)
);

create table orders
(
    id         serial primary key,
    active     boolean,
	order_date date,
    book_id    integer references books (id),
    student_id integer references students (id)
);

insert into students (name) values 
('Иван Иванов'),
('Петр Петров');

insert into authors (name) values
('Александр Пушкин'),
('Николай Гоголь'),
('Иван Тургенев'),
('Антон Чехов');

insert into books (name, author_id) values 
('Евгений Онегин', 1),
('Капитанская дочка', 1),
('Дубровский', 1),
('Мертвые души', 2),
('Вий', 2);

insert into orders (active, order_date, book_id, student_id) values 
(true, '2025-01-15', 1, 1),
(true, '2025-02-20', 2, 1),
(true, '2025-03-10', 3, 1),
(false, '2025-02-25', 4, 2),
(true, '2025-03-05', 5, 2);