drop table employees, departments;
create table departments (
    id   serial primary key,
    name varchar(255)
);

create table employees (
    id            serial primary key,
    name          varchar(255),
    department_id int references departments(id)
);

insert into departments (name) values
('HR'),
('IT'),
('Финансы'),
('Маркетинг');

insert into employees (name, department_id) values
('Алиса', 1),
('Боб', 2),
('Чарли', 2),
('Давид', 3);

select *
from employees e
left join departments d on e.department_id = d.id;

select *
from departments d
right join employees e on e.department_id = d.id;

select *
from departments d
full join employees e on e.department_id = d.id;

select *
from departments d cross join employees;

select *
from departments d
left join employees e on e.department_id = d.id
where e.id is null;

select *
from employees e
left join departments d on e.department_id = d.id;

select e.*, d.*
from departments d
right join employees e on e.department_id = d.id;

create table teens (
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);

insert into teens (name, gender) values
('Алиса', 'женский'),
('Боб', 'мужской'),
('Чарли', 'мужской'),
('Диана', 'женский');

select m.name as "Имя", f.name as "Имя", (m.name || '-' || f.name) as "Пара"
from teens m cross join teens f
where m.gender != f.gender
and m.name < f.name;

