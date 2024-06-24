-- -----------------------------------------------------------------------------
-- 18.09.2008
--
-- (C) Eclipse SP LLC. All rights reserved
-- 
-- This script creates tournament related tables
--
-- $Revision: 240 $
-- $Author: dsharanutsa_tckh $
-- $Date: 2008-07-28 10:48:59 +0300 (Пн, 28 июл 2008) $
-- -----------------------------------------------------------------------------

-- Defines a status for tournaments and users
create table `status` (
	`id` bigint not null auto_increment,
	`status_name` varchar(30) not null,
	`type` varchar(30),
	primary key (`id`)
)
engine=InnoDB default charset=utf8;

-- Defines a team
create table `team` (
	`id` bigint not null auto_increment,
	`team_name` varchar(65) not null,
	`account_id` bigint,
	primary key (`id`)
)
engine=InnoDB default charset=utf8;

-- Defines a tournament
create table `tournament`(
	`id` bigint not null auto_increment,
	`status_id` bigint,
	`start_date` datetime,
	`finish_date` datetime,
	`game_kind` varchar(30),
	`account_id` bigint,
	`tournament_name` varchar(100),
	`visibility` varchar(20),
	`description` varchar(255),
	primary key(`id`)
)
engine=InnoDB default charset=utf8;

-- Defines a promo
create table `promo` (
	`tournament_id` bigint not null,
	`promo` varchar(120),
	primary key (`tournament_id`,`promo`)
)
engine=InnoDB default charset=utf8;

-- Defines a stage
create table `stage` (
  	`id` bigint not null auto_increment,
	`tournament_id` bigint not null,
	`stage_name` varchar(100),
	`creation_date` datetime,
	primary key (`id`)
)
engine=InnoDB default charset=utf8;

-- Defines a group
create table `group` (
	`id` bigint not null auto_increment,
	`group_name` varchar(100),
	primary key (`id`)
)
engine=InnoDB default charset=utf8;


-- Defines user for the game
create table `gameuser`(
	`id` bigint not null auto_increment,
	`group_id` bigint not null,
	`score_1` bigint,
	`score_2` bigint,
	`winner` bigint,
	`user_1` bigint,
	`user_2` bigint,
	`date` datetime,
	primary key (`id`)
)
engine=InnoDB default charset=utf8;

-- Defines team for the game
create table `gameteam` (
	`id` bigint not null auto_increment,
	`group_id` bigint not null,
	`score_1` bigint,
	`score_2` bigint,
	`winner` bigint,
	`team_1` bigint,
	`team_2` bigint,
	`date` datetime,
	primary key (`id`)
)
engine=InnoDB default charset=utf8;

alter table `tournament` add constraint `fk_tournament_account`
  foreign key (`account_id`) references `account` (`id`)
	on delete cascade;

alter table `team` add constraint `fk_team_account`
	foreign key (`account_id`) references `account` (`id`)
	on delete set null;

alter table `promo` add constraint `fk_promo_tournament`
	foreign key (`tournament_id`) references `tournament` (`id`)
	on delete cascade;

alter table `gameuser` add constraint `fk_game_user_1`
	foreign key (`user_1`) references `account` (`id`)
	on delete set null;

alter table `gameuser` add constraint `fk_game_user_2`
	foreign key (`user_2`) references `account` (`id`)
	on delete set null;

alter table `gameuser` add constraint `fk_game_user_winner`
	foreign key (`winner`) references `account` (`id`)
	on delete set null;

alter table `gameuser` add constraint `fk_game_user_group`
	foreign key (`group_id`) references `group` (`id`)
	on delete restrict;

alter table `gameteam` add constraint `fk_game_team_1`
	foreign key (`team_1`) references `team` (`id`)
	on delete set null;

alter table `gameteam` add constraint `fk_game_team_2`
	foreign key (`team_2`) references `team` (`id`)
	on delete set null;

alter table `gameteam` add constraint `fk_game_team_winner`
	foreign key (`winner`) references `team` (`id`)
	on delete set null;

alter table `gameteam` add constraint `fk_game_team_group`
	foreign key (`group_id`) references `group` (`id`)
	on delete restrict;

alter table `stage` add constraint `fk_stage_tournament`
	foreign key (`tournament_id`) references `tournament` (`id`)
	on delete cascade;