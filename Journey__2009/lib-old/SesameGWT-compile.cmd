SET JAVA_HOME=C:\Progra~1\Java\jdk1.8.0
SET JRE_HOME=C:\Progra~1\Java\jdk1.8.0\jre
SET Path=%JAVA_HOME%\bin;$PATH
@java -Xmx96M -cp "%~dp0\src;%~dp0\bin;%~dp0/lib-gwt/gwt-user.jar;%~dp0/lib-gwt/gwt-dev-windows.jar;%~dp0/lib-gwt/gwt-servlet.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\WebContent" %* journey.gwt.SesameGWT