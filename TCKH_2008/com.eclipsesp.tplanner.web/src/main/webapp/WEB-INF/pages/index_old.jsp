<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<!-- index.jsp -->
	
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/xhtml; charset=UTF-8" />
	
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>" />
	
	<!-- MessageBundle -->
	<fmt:setBundle basename="com.eclipsesp.tplanner.web.messages.home"/>
	
	<title>
  			<fmt:message key="tplanner.title"/>
	</title>
</head>
<body>	
	<div>
		<h1>		
			<fmt:message key="tplanner.title"/>	
		</h1>
	</div>
	
	<ul class="menu_">
		<li>
			<a href="login.jsp">
				<fmt:message key="login.title"/>
			</a>
		</li>		
		<li>
			<a href="users.jsp">
				<fmt:message key="users.title"/>
			</a>
		</li>	
		<li>
			<a href="tournaments.jsp?ownerId=-1">
				<fmt:message key="tournament.title"/>
			</a>
		</li>	
		<li>
			<a href="registration.jsp">
				<fmt:message key="registration.title"/>
			</a>
		</li>	
		<li>
			<a href="myaccount.html">
				<fmt:message key="myProfile.title"/>
			</a>
		</li>
	</ul>
</body>
</html>