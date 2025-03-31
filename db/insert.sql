insert into roles(name_role) values('Jester');
insert into rules(name_rule) values('Jester - Entertainer who brings humor and wisdom');
insert into states(name_state) values('Approved');
insert into categories(name_category) values('Players');
insert into users(name, role_id) values('Java', 1);
insert into roles_rules(role_id, rule_id) values(1, 1);
insert into items(name_item, user_id, category_id, state_id) values('Joke Request', 1, 1, 1);
insert into comments(text_comment, item_id) values('xa-xa-xa', 1);
insert into attachs(file_path, item_id) values('Joke.txt', 1);