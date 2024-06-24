@echo off

rem ****************************************************************************
rem * 17.08.2008
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
rem * $Revision: 156 $
rem * $Author: pmarinchev_tckh $
rem * $Date: 2008-07-24 13:18:36 +0300 (Чт, 24 июл 2008) $
rem ****************************************************************************

IF [%3] EQU [] GOTO NoParams

echo Re-create the database and re-grant the permissions
mysql -u root -p -e "drop database if exists `%1`;create database `%1` character set utf8;grant all on `%1`.* to `%2`@`localhost` identified by '%3';flush privileges;"

echo Create role and account related tables
mysql -u %2 --password=%3 %1 < ..\sql\security.sql
echo Create tournament related tables
mysql -u %2 --password=%3 %1 < ..\sql\tournament.sql
echo Create relations tables
mysql -u %2 --password=%3 %1 < ..\sql\relations.sql
echo Create default accounts
mysql -u %2 --password=%3 %1 < ..\sql\default-accounts.sql


GOTO End

:NoParams
echo Not all parameters were specified.
echo.
echo Usage:
echo.      recreate.cmd "database name" "user name" "user password"

:End
echo Done
