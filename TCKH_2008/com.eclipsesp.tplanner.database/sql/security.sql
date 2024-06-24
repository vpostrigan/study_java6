-- -----------------------------------------------------------------------------
-- 18.09.2008
--
-- (C) Eclipse SP LLC. All rights reserved
-- 
-- This script creates tables which are related 
-- to the account and role management.
--
-- $Revision: 539 $
-- $Author: vpostrigan_tckh $
-- $Date: 2008-08-05 19:03:13 +0300 (Вт, 05 авг 2008) $
-- -----------------------------------------------------------------------------

-- Defines metadata stored current version of database.
create table `metadata` (
	`version` bigint not null,
	primary key (`version`)
)
engine=InnoDB default charset=utf8;

-- Defines security role.
create table `role` (
	`role_name` varchar(65) not null,
	`permission_def` smallint,
	primary key (`role_name`)
)
engine=InnoDB default charset=utf8;

-- Defines an account in the system.
create table `account` (
	`id` bigint not null auto_increment,
	`email` varchar(40) not null,
	`firstname` varchar(100) not null default '',
	`lastname` varchar(100),
	`nick` varchar(100),
	`password` varchar(41) not null default '',
	`city` varchar(20),
	`address` varchar(100),
	`zip` varchar(30),
	`role_name` varchar(65),
	`description` varchar(300),
	primary key (`id`),
	unique `uq_account_email` (`email`)
)
engine=InnoDB default charset=utf8;

-- Index of user's name.
create index `ix_account_name` on `account` (`firstname` asc, `lastname` asc, `nick` asc);

-- ------------ --
-- FOREIGN KEYS --
-- ------------ --
alter table `account` add constraint `fk_role_account`
  foreign key (`role_name`) references `role` (`role_name`)
  on delete set null;