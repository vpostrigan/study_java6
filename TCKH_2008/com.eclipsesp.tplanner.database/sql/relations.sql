-- -----------------------------------------------------------------------------
-- 18.09.2008
--
-- (C) Eclipse SP LLC. All rights reserved
-- 
-- This script creates tables that implement many-to-many relationship between key tournaments tables 
--
-- $Revision: 122 $
-- $Author: dproshkin_tckh $
-- $Date: 2008-07-22 11:05:25 +0300 (Вт, 22 июл 2008) $
-- -----------------------------------------------------------------------------

-- user2team
create table `user2team` (
	`team_id` bigint not null,
	`account_id` bigint not null,
	`status_id` bigint,
	primary key (`team_id`, `account_id`)
)
engine=InnoDB default charset=utf8;

-- Defines a stage2group
create table `stage2group`(
	`group_id` bigint not null,
	`stage_id` bigint not null,
	primary key (`group_id`, `stage_id`)
)
engine=InnoDB default charset=utf8;

-- Defines a user2group
create table `user2group` (
	`group_id` bigint not null,
	`account_id` bigint not null,
	primary key (`group_id`,`account_id`)
)
engine=InnoDB default charset=utf8;

-- Defines a user2tournament
create table `user2tournament` (
	`tournament_id` bigint not null,
	`account_id` bigint not null,
	`status_id` bigint,
	primary key (`account_id`, `tournament_id`)
)
engine=InnoDB default charset=utf8;

-- Defines team to tournament
create table `team2tournament` (
	`team_id` bigint not null,
	`tournament_id` bigint not null,
	`status_id` bigint,
	primary key (`team_id`, `tournament_id`)
)
engine=InnoDB default charset=utf8;

-- Defines team to group
create table `team2group` (
	`team_id` bigint not null,
	`group_id` bigint not null,
	primary key (`group_id`, `team_id`)
)
engine=InnoDB default charset=utf8;

-- ------------ --
-- FOREIGN KEYS --
-- ------------ --

alter table `team2group` add constraint `fk_team2group_group`
	foreign key (`group_id`) references `group` (`id`)
	on delete cascade;

alter table `team2group` add constraint `fk_team2group_team`
	foreign key (`team_id`) references `team` (`id`)
	on delete cascade;

alter table `user2team` add constraint `fk_user2team_account`
  foreign key (`account_id`) references `account` (`id`)
	on delete cascade;

alter table `user2team` add constraint `fk_user2team_status`
	foreign key (`status_id`) references `status` (`id`)
  on delete set null;

alter table `user2team` add constraint `fk_user2team_team`
	foreign key (`team_id`) references `team` (`id`)
	on delete cascade;

alter table `stage2group` add constraint `fk_stage2group_group`
	foreign key (`group_id`) references `group` (`id`)
	on delete cascade;

alter table `stage2group` add constraint `fk_stage2group_stage`
	foreign key (`stage_id`) references `stage` (`id`)
	on delete cascade;

alter table `user2group` add constraint `fk_user2group_group`
	foreign key (`group_id`) references `group` (`id`)
	on delete cascade;

alter table `user2group` add constraint `fk_user2group_account`
	foreign key (`account_id`) references `account` (`id`)
	on delete cascade;

alter table `user2tournament` add constraint `fk_user2tournament_tournament`
	foreign key (`tournament_id`) references `tournament` (`id`)
	on delete cascade;

alter table `user2tournament` add constraint `fk_user2tournament_account`
	foreign key (`account_id`) references `account` (`id`)
	on delete cascade;

alter table `user2tournament` add constraint `fk_user2tournament_status`
	foreign key (`status_id`) references `status` (`id`)
	on delete set null;

alter table `team2tournament` add constraint `fk_team2tournament_team_id`
	foreign key (`team_id`) references `team` (`id`)
	on delete cascade;

alter table `team2tournament` add constraint `fk_team2tournament_tournament_id`
	foreign key (`tournament_id`) references `tournament` (`id`)
	on delete cascade;

alter table `team2tournament` add constraint `fk_team2tournament_status_id`
	foreign key (`status_id`) references `status` (`id`)
	on delete set null;