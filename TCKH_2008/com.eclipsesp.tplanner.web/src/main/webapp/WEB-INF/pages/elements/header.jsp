<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/tplanner.tld" prefix="tplanner"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

 <?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<fmt:message key="static.header.jsp.tplanner"/>
</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>"/>
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/PopupCalendar.css"/>" />
	
	<!-- js -->
	<script type="text/javascript" src="<c:url value="/js/inputFocus_forIE.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/showAndHideDetails.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/searchInTable.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/PopupCalendar.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/sortable.js"/>"></script>
</head>
<body>
<div id="wrapper">
	<div id="topbar">
	
    	<div class="welcome-msg"> <tags:welcome/><strong><!-- Add user name here if any --></strong></div>	
    	+380 57 716 45 85  |  <a href="mailto:info@eclipse-sp.com">info@eclipse-sp.com</a>  |  <a href="http://www.eclipse-sp.ua"> www.eclipse-sp.ua</a>
    </div>
    <div id="header">
    
    	<a href="../pages/index.jsp"> 
    		<div id="logo">
        		<strong>Tournament</strong> Planner
           		<span><fmt:message key="static.header.jsp.createPlanPlay"/></span>
       		</div>
    	</a>
    	
        <div id="alpha-note">
        	<fmt:message key="static.header.jsp.alpha"/>
        </div>
        
        <ul id="navigation">
  			<tags:menuInHeader/>
        </ul>
        
        <div id="menu">
			<tags:loginMenu/>
        </div>
        
        <br /><br /><br />
		<div class="blockRight_">
			<a href="index.changeLanguage?language=ua">
				<img src="../images/country/ua.jpg" alt="" border="0" align="middle"/>
			</a>
			<a href="index.changeLanguage?language=ru">
				<img src="../images/country/ru.jpg" alt="" border="0" align="middle"/>
			</a>
			<a href="index.changeLanguage?language=en">
				<img src="../images/country/en.jpg" alt="" border="0" align="middle"/>
			</a>
			&nbsp;
		</div>
    </div>
    <div id="body">