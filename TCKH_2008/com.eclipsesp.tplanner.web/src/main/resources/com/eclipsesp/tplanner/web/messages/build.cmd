@echo off

rem ****************************************************************************
rem * 22.07.2008
rem * 
rem * (C) Eclipse SP LLC. All rights reserved
rem * 
rem * This script compiles java resources from native encoding to
rem * ASCII encoding.
rem * 
rem * $Revision: 398 $
rem * $Author: vpostrigan_tckh $
rem * $Date: 2008-08-01 15:14:05 +0300 (Пт, 01 авг 2008) $
rem ****************************************************************************

if "%JAVA_HOME%" == "" goto nojavahome
cd txt
for %%f in (*_ru) do "%JAVA_HOME%\bin\native2ascii" -encoding utf-8 "%%f" "..\%%f.properties"
cd ..
exit 0

:nojavahome
echo JAVA_HOME is invalid
exit 1