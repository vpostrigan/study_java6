<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- index.jsp -->

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Main.css -->
	<link rel="stylesheet" type="text/css" href="css/Login.css" />
	
	<title>
		<bean:message key="student"/>
	</title>
</head>
<body>

	<script type="text/javascript">
		sfFocus = function() {
			var sfEls = document.getElementsByTagName("INPUT");
			for (var i=0; i<sfEls.length; i++) {
				sfEls[i].onfocus=function() {
					this.className+=" sffocus";
				}
				sfEls[i].onblur=function() {
					this.className=this.className.replace(new RegExp(" sffocus\\b"), "");
				}
			}
		}
		if (window.attachEvent) window.attachEvent("onload", sfFocus);		
	</script>

	<table width=100% border=0>
		<tr>
			<td class="logo_">  </td>			
			<td class="address_"> <bean:message key="static.authorization"/> </td>			
		</tr>		
	</table>
	<br />
	
	
	<div class="error_">
		<html:errors property="error.db"/>
	</div>
	
	<table border="0">	
		<html:form action="login.do" method="post">
			<tr>
				<td></td>
				<td align="right"> 
					<html:link action="ChangeLocale?language=ua">
						<html:img src="css/img/ua.jpg" alt="" border="0" align="absmiddle"/>
					</html:link>
					<html:link action="ChangeLocale?language=ru">
						<html:img src="css/img/ru.jpg" alt="" border="0" align="absmiddle"/>
					</html:link>
					<html:link action="ChangeLocale?language=en" >
						<html:img src="css/img/en.jpg" alt="" border="0" align="absmiddle"/>
					</html:link> 
				</td>
			</tr>
			<tr>
				<td class="textStatic_"> <bean:message key="static.authorization.login"/><bean:message key="colon"/> </td>
				<td> 
					<html:text maxlength="20" styleClass="textDynamic_" property="login" tabindex="1"/>
					<span class="errorIncorrectValue_"> <html:errors property="error.authorization.login" /> </span>
				</td>
			</tr>
			<tr>
				<td class="textStatic_"> <bean:message key="static.authorization.password"/><bean:message key="colon"/> </td>
				<td> <html:password maxlength="20" styleClass="textDynamic_" property="password" tabindex="2"/> </td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<html:submit styleClass="textStatic_" tabindex="3"> 
						<bean:message key="static.authorization.submit"/> 
					</html:submit>					
				</td>
			</tr>
		</html:form>	
	</table>
	
	<div class="errorIncorrectValue_"> <html:errors property="error.authorization.incorrect.login" /> </div>
	<div class="errorIncorrectValue_"> <html:errors property="error.authorization.incorrect.password" /> </div>

</body>
</html:html>