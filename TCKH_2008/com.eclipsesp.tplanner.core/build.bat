@echo off

title Building the Tplanner...

cls

@echo.
@echo Building the Tplanner...
@echo.

"%JAVA_HOME%/bin/java" -cp lib-ant/ant.jar;lib-ant/ant-launcher.jar;lib-ant/ant-trax.jar;lib-ant/ant-junit.jar;"%JAVA_HOME%/lib/tools.jar" org.apache.tools.ant.Main %1

@echo.
