create or replace trigger calculated_tax_after_insert
	after insert on products
	referencing new table as temp_table
	for each statement
	execute function tax_after();

create or replace trigger calculated_tax_before_insert
before insert on products
for each row
execute function tax_before();

create or replace trigger copy_data_from_products_to_history_of_price
after insert on products
for each row
execute function copy_after_insert();