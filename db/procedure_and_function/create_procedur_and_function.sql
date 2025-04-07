create or replace procedure delete_by_using_procedure()
language 'plpgsql' as
$$
	begin
		delete from products
		where count = 0;
	end;
$$;

create or replace function delete_by_using_function(d_id integer)
returns void
language 'plpgsql' as
$$
	begin
		delete from products
		where id = d_id;
	end;
$$;