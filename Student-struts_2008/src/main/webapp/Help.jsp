<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- Help.jsp -->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Profile.css -->
	<link rel="stylesheet" type="text/css" href="css/Profile.css" />
	
	<title>
		<bean:message key="student"/>
	</title>
</head>
<body>
	
	<table width=100% border=0>
		<tr>
			<td class="logo_">  </td>
			<td>
				<html:link styleClass="address_" action="main.do">
					<bean:message key="static.mainpage"/>
				</html:link>
				<bean:message key="bigger"/>
				<span class="address_"> <bean:message key="static.mainpage.help"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
		<div class="textValue_">
			<a href="http://www.google.com/">Google</a> <bean:message key="help"/>
			<html:img src="css/img/smile.gif" alt="" border="0" align="absmiddle" />
		</div>
	
</body>
</html>