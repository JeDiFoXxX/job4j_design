create table email(
    id serial primary key,
    name varchar(255)
);

create table user_system(
    id serial primary key,
	name varchar(255),
	email_id int references email(id) unique
);

insert into email(name) values ('Java@gmail.com');
insert into user_system(name, email_id) values ('Java', 1);

select * from user_system;