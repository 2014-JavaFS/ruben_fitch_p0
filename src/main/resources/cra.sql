-- CRUD - Create, Read, Update, Delete (soft/hard)
-- shortcut: ctrl+enter will run a single SQL statement
-- drops

drop table members;

-- CREATE - Keywords: create, insert, alter
create table members(
	member_id integer primary key,
	first_name varchar,
	last_name varchar,
	member_type varchar, -- replace with enum later
	password varchar
);


insert into members(member_id, first_name, last_name, password)
values(2000, 'Ruben', 'Fitch', 'password');

insert into members(member_id, first_name, last_name, password)
values(1000, 'Abraham', 'Lincoln', 'fourscore');

insert into members(member_id, first_name, last_name, password)
values(3000, 'George', 'Washington', 'cannotlie');

insert into members(member_id, first_name, last_name, password)
values(4000, 'Teddy', 'Roosevelt', 'bully');

insert into members(member_id, first_name, last_name, password)
values(1234, 'Dummy', 'Test', '4321');

-- READ
select * from members;

-- UPDATE WHENVER you update make sure you have a WHERE clause
update members
	set first_name = 'Ruben'
	set last_name = 'Fitch'
	where member_id = 2000;

select * from members;

-- DELETE ANYTIME you use a delete make sure you have a WHERE clause
delete from members
	where member_id = 1234;

select * from members;

-- specifically want to use TRUNCATE when we want to remove/delete all records in a table
truncate table members;

select * from members;