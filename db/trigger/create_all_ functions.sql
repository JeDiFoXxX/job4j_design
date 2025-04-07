create or replace function tax_after()
returns trigger as 
$$
	begin
		update products
		set price = price + price * 0.2
		where id = (select id from temp_table);
		return new;
	end;
$$
language plpgsql;

create or replace function tax_before()
returns trigger as
$$
	begin 
		new.price = new.price + new.price * 0.2;
		return new;
	end;
$$
language plpgsql;

create or replace function copy_after_insert()
returns trigger as
$$
	begin
		insert into history_of_price(name, price, date) values
		(new.name, new.price, current_timestamp);
		return new;
	end;
$$
language plpgsql;