@echo off

title Building the Tplanner...

cls

@echo.
@echo Building the Tplanner...
@echo.

"%JAVA_HOME%/bin/java" -cp ../com.eclipsesp.tplanner.core/lib-ant/ant.jar;../com.eclipsesp.tplanner.core/lib-ant/ant-launcher.jar;../com.eclipsesp.tplanner.core/lib-ant/ant-trax.jar;../com.eclipsesp.tplanner.core/lib-ant/ant-junit.jar;../com.eclipsesp.tplanner.core/lib-antsvn/svnkit-cli.jar"%JAVA_HOME%/lib/tools.jar" org.apache.tools.ant.Main %1
@echo. 