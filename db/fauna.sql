create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values
('Lion', 14, '1758-01-01'),
('Tiger', 15, '1758-01-01'),
('Elephant', 70, '1773-06-12'),
('Polar Bear', 25, '1774-09-18'),
('Crocodile', 80, NULL),
('Eagle', 30, '1758-01-01'),
('Wolf', 16, '1758-01-01'),
('Panda', 20, '1869-03-11'),
('Cheetah', 12, '1775-08-22'),
('Kangaroo', 23, NULL),
('Goldfish', 30, NULL);

select name from fauna where name like '%fish%';
select name, avg_age from fauna where avg_age > 10 and avg_age < 21;
select name, discovery_date from fauna where discovery_date is null;
select name, discovery_date from fauna where discovery_date < '01.01.1950';