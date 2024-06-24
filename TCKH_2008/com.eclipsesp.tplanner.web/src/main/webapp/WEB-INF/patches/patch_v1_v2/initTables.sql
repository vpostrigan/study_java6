
-- Insert new database version
insert into `metadata` set `version`='2';

-- Inserting needed records for minimal application working
insert into `role` values 
	('administrators', 255), 
	('users', 119);

insert into `status` values 
	('1', 'pending', 'tournament');

insert into `status` values 
	('2', 'started', 'tournament');

insert into `status` values 
	('3', 'finished', 'tournament');

insert into `status` values 
	('4', 'invited', 'user');

insert into `status` values 
	('5', 'accepted', 'user');

insert into `status` values 
	('6', 'finished', 'user');

