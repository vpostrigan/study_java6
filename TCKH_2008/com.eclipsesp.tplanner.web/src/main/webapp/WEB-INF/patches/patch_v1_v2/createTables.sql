USE `tplanner`;

-- -- -- -- security.sql -- -- -- --


-- Defines metadata stored current version of database.
create table `metadata` (
	`version` bigint not null,
	primary key (`version`)
)
type=InnoDB default charset=utf8;

-- Defines security role.
create table `role` (
	`role_name` varchar(65) not null,
	`permission_def` smallint,
	primary key (`role_name`)
)
type=InnoDB default charset=utf8;

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
type=InnoDB default charset=utf8;

-- Index of user's name.
create index `ix_account_name` on `account` (`firstname` asc, `lastname` asc, `nick` asc);

-- ------------ --
-- FOREIGN KEYS --
-- ------------ --
alter table `account` add constraint `fk_role_account`
  foreign key (`role_name`) references `role` (`role_name`)
  on delete set null;


-- -- -- -- tournament.sql -- -- -- --


-- Defines a status for tournaments and users
create table `status` (
	`id` bigint not null auto_increment,
	`status_name` varchar(30) not null,
	`type` varchar(30),
	primary key (`id`)
)
type=InnoDB default charset=utf8;

-- Defines a team
create table `team` (
	`id` bigint not null auto_increment,
	`team_name` varchar(65) not null,
	`account_id` bigint,
	primary key (`id`)
)
type=InnoDB default charset=utf8;

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
type=InnoDB default charset=utf8;

-- Defines a promo
create table `promo` (
	`tournament_id` bigint not null,
	`promo` varchar(120),
	primary key (`tournament_id`,`promo`)
)
type=InnoDB default charset=utf8;

-- Defines a stage
create table `stage` (
  	`id` bigint not null auto_increment,
	`tournament_id` bigint not null,
	`stage_name` varchar(100),
	`creation_date` datetime,
	primary key (`id`)
)
type=InnoDB default charset=utf8;

-- Defines a group
create table `group` (
	`id` bigint not null auto_increment,
	`group_name` varchar(100),
	primary key (`id`)
)
type=InnoDB default charset=utf8;


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
type=InnoDB default charset=utf8;

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
type=InnoDB default charset=utf8;

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


-- -- -- -- relations.sql -- -- -- --


-- user2team
create table `user2team` (
	`team_id` bigint not null,
	`account_id` bigint not null,
	`status_id` bigint,
	primary key (`team_id`, `account_id`)
)
type=InnoDB default charset=utf8;

-- Defines a stage2group
create table `stage2group`(
	`group_id` bigint not null,
	`stage_id` bigint not null,
	primary key (`group_id`, `stage_id`)
)
type=InnoDB default charset=utf8;

-- Defines a user2group
create table `user2group` (
	`group_id` bigint not null,
	`account_id` bigint not null,
	primary key (`group_id`,`account_id`)
)
type=InnoDB default charset=utf8;

-- Defines a user2tournament
create table `user2tournament` (
	`tournament_id` bigint not null,
	`account_id` bigint not null,
	`status_id` bigint,
	primary key (`account_id`, `tournament_id`)
)
type=InnoDB default charset=utf8;

-- Defines team to tournament
create table `team2tournament` (
	`team_id` bigint not null,
	`tournament_id` bigint not null,
	`status_id` bigint,
	primary key (`team_id`, `tournament_id`)
)
type=InnoDB default charset=utf8;

-- Defines team to group
create table `team2group` (
	`team_id` bigint not null,
	`group_id` bigint not null,
	primary key (`group_id`, `team_id`)
)
type=InnoDB default charset=utf8;

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

