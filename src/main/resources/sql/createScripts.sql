DROP TABLE if exists household.todo cascade;
DROP TABLE if exists household.household_member cascade;

create table household.household_member (
	email varchar(128) not null primary key,
	fName varchar(77) not null,
	lName varchar(77) not null	
) without oids;
alter table household.household_member owner to postgres;

create table household.todo (
	id serial primary key,
	type varchar(4) not null check(type = 'Todo' or type = 'Note' or type = 'List'),
	recurrence varchar(15) not null check(recurrence = 'One-Time' or recurrence = 'Daily' or recurrence = 'Weekly' or recurrence = 'Bi-Weekly' or recurrence = 'Semi-Monthly'
	 or recurrence = 'Monthly' or recurrence = 'quarterly' or recurrence = 'Semi-Annually' or recurrence = 'Annually'),
	created_by varchar(128) not null references household.household_member(email) on delete cascade on update cascade,
	created_on timestamp without time zone not null default now(),
	value text not null,
	due_by timestamp without time zone default null,
	is_complete boolean default false
) without oids;
alter table household.todo owner to postgres;

insert into household.household_member values('macdersonlouis@gmail.com', 'MacDerson', 'Louis');
insert into household.todo values(0,'Todo', 'One-Time', 'macdersonlouis@gmail.com', now(), 'buy gift for Fatima', now(), false);

update household.todo set id = 1 where id = 0;