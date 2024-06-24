@echo off

rem ****************************************************************************
rem * 04.08.2008
rem * 
rem * (C) Eclipse SP LLC. All rights reserved
rem * 
rem * This command script re-creates the database.
rem *
rem * Arguments:
rem *	%1 The name of the database to re-create
rem *	%2 Username for application user in database
rem *	%3 Password for application user in database
rem * 
rem * $Revision:  $
rem * $Author: dsharanutsa_tckh $
rem * $Date:  $
rem ****************************************************************************

IF [%3] EQU [] GOTO NoParams

echo Re-create the database and re-grant the permissions
mysql -u root -p -e "drop database if exists `%1`;create database `%1` character set utf8;grant all on `%1`.* to `%2`@`localhost` identified by '%3';flush privileges;" --password=%3

echo Create role and account related tables
mysql -u %2 --password=%3 %1 < ..\sql\security.sql
echo Create tournament related tables
mysql -u %2 --password=%3 %1 < ..\sql\tournament.sql
echo Create relations tables
mysql -u %2 --password=%3 %1 < ..\sql\relations.sql
echo Create default accounts
mysql -u %2 --password=%3 %1 < ..\sql\default-accounts.sql
echo Create default team
mysql -u %2 --password=%3 %1 <..\sql\default\default-team.sql
echo Create default status
mysql -u %2 --password=%3 %1 <..\sql\default\default-status.sql
echo Create default group
mysql -u %2 --password=%3 %1 <..\sql\default\default-group.sql
echo Create default tournament
mysql -u %2 --password=%3 %1 <..\sql\default\default-tournament.sql
echo Create default promo
mysql -u %2 --password=%3 %1 <..\sql\default\default-promo.sql
echo Create default stage
mysql -u %2 --password=%3 %1 <..\sql\default\default-stage.sql



GOTO End

:NoParams
echo Not all parameters were specified.
echo.
echo Usage:
echo.      recreateDB.bat "tplanner" "root" "qwerty"

:End
echo Done
