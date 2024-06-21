<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/reset.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>">
	
	<!-- js -->
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAANvMtCXj3Mz5mObi5cXPQkBRJF-1HJdDcgNTxyKGp2PGDDa_ErhQ5aGARUR74eZjuE3YticHa-GIFbA"
      type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/js/setMapLocation.js"/>"></script>
		
	<title>
		<fmt:message key="header.jsp.journey"/>
	</title>
</head>
<body onload="initialize()" onunload="GUnload()">
	<div id="content">
		<div id="logo"></div>
		
		<div id="menu">
			<div id="lang">
				<a href="index.changeLanguage?language=ua">
					<img src="../image/country/ua.jpg" alt="" border="0" align="middle"/>
				</a>
				<a href="index.changeLanguage?language=ru">
					<img src="../image/country/ru.jpg" alt="" border="0" align="middle"/>
				</a>
				<a href="index.changeLanguage?language=en">
					<img src="../image/country/en.jpg" alt="" border="0" align="middle"/>
				</a>
			</div>
			
			<ul>
			<c:if test="${empty sessionScope.account}">
				<li>
					<a class="${param.selectedPage == '/pages/index.jsp' ? 'current' : 'notCurrent'}"
							href="<c:url value="/pages/index.jsp" />">
						<fmt:message key="header.jsp.home"/>
					</a>
				</li>
				<li>
					<a class="${param.selectedPage == '/pages/join.jsp' ? 'current' : 'notCurrent'}"
							href="<c:url value="/pages/join.jsp" />">
						<fmt:message key="header.jsp.join"/>
					</a>
				</li>
				<li>
					<a class="${param.selectedPage == '/pages/about.jsp' ? 'current' : 'notCurrent'}"
							href="<c:url value="/pages/about.jsp" />">
						<fmt:message key="header.jsp.about"/>
					</a>
				</li>
				<li>
					<a class="${param.selectedPage == '/pages/login.jsp' ? 'current' : 'notCurrent'}"
							href="<c:url value="/pages/login.jsp" />">
						<fmt:message key="header.jsp.login"/>
					</a>
				</li>
			</c:if>
			<c:if test="${not empty sessionScope.account}">
				<li>
					<a class="${param.selectedPage == '/pages/about.jsp' ? 'current' : 'notCurrent'}"
							href="<c:url value="/pages/about.jsp" />">
						<fmt:message key="header.jsp.about"/>
					</a>
				</li>
			</c:if>
			</ul>
		</div>

		<div class="clear"></div>
