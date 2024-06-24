<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		<!-- error.jsp -->
	
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/xhtml; charset=UTF-8" />
	
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>" />
	
	<title>
		<fmt:bundle basename="com.eclipsesp.tplanner.web.messages.home">
  			<fmt:message key="error.title"/>
		</fmt:bundle>
	</title>
</head>
<body>
	<p class="mainError"><fmt:message key="error.unknownError"/></p>
</body>
</html>