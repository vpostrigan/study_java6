<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/tplanner.tld" prefix="tplanner"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

	<!-- 500.JSP --> 

 <?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<!-- MessageBundle -->
	<fmt:setBundle basename="com.eclipsesp.tplanner.web.messages.home"/>

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
	
    	<div class="welcome-msg"> <strong><!-- Add user name here if any --></strong></div>	
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
  			<a href="../pages/index.jsp">
				<fmt:message key="tplanner.title"/>
			</a>
			&nbsp;
        </ul>
    </div>
    <div id="body">

	<c:choose>
		<c:when test="${not empty pageContext.exception}">
			<c:set var="problemType"><fmt:message key="errorPage.JSPException"/></c:set>			
			<c:set var="appException" value="${pageContext.exception}"/>
			<c:set var="causeException" value="${appException.cause}"/>
		</c:when>
		<c:when test="${not empty requestScope['javax.servlet.error.exception']}">
			<c:set var="problemType"><fmt:message key="errorPage.servletException"/></c:set>
			<c:set var="appException" value="${requestScope['javax.servlet.error.exception']}"/>
			<c:set var="causeException" value="${appException.rootCause}"/>			
		</c:when>
		<c:otherwise>
			<c:set var="problemType"><fmt:message key="errorPage.unidentifiedServerError"/></c:set>
		</c:otherwise>
	</c:choose>
	
	<div id="content">
		<p class="errorNumber_">
			<fmt:message key="errorPage.error500"/>
		</p>
	
		<h1><fmt:message key="errorPage.systemProblem"/></h1>
	        <p>
	    		<fmt:message key="errorPage.error500SystemProblem"/>
	        </p>
		<h2><fmt:message key="errorPage.problemType"/></h2>	  
			<c:if test="${not empty problemType}">
				<p><c:out value="${problemType}"/></p>
			</c:if>
			
		<h2><fmt:message key="errorPage.problemDetails"/></h2>	  
			<c:if test="${not empty requestScope['javax.servlet.error.message']}">
				<p>
					<c:out value="${requestScope['javax.servlet.error.message']}"/>
				</p>
			</c:if>	
			<c:if test="${not empty appException}">
				<p>
					<c:out value="${appException.message}"/>
				</p>
			</c:if>
		
		<c:if test="${not empty causeException}">
			<h2><fmt:message key="errorPage.causedBy"/></h2>	  
			<p>
				<c:out value="${causeException}"/>
			</p>
			
			<h2><fmt:message key="errorPage.causedDetails"/></h2>
			<p>
				<c:out value="${causeException.message}"/>
			</p>
		</c:if>
		
		<a id="showDetailsLinkDiv" href="javascript:showDetails()">			
			<p>
				[ <fmt:message key="errorPage.showDetails"/> ]
			</p>
		</a>
		<a id="hideDetailsLinkDiv" href="javascript:hideDetails()">
			<p>
				[ <fmt:message key="errorPage.hideDetails"/> ]
			</p>
		</a>
		
		<div id="stackTraceDiv">
			<c:if test="${not empty appException}">
				<h2><fmt:message key="errorPage.exceptionStackTrace"/></h2>
				<p>
					<c:out value="${appException}"/>
				</p>
				<ul>
					<c:forEach var="stackItem" items="${appException.stackTrace}">
					<li>
						<c:out value="${stackItem}"/>
					</li>
					</c:forEach>
				</ul>				
			</c:if>
			<c:if test="${not empty causeException}">
				<h2><fmt:message key="errorPage.causeStackTrace"/></h2>	  
				<p>
					<c:out value="${causeException}"/>
				</p>
				<ul>
					<c:forEach var="stackItem" items="${causeException.stackTrace}">
					<li>
						<c:out value="${stackItem}"/>
					</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		
	<div class="clear">

		<div id="footer">
            <span><a href="#"><fmt:message key="footer.feedBack"/></a></span> | 
            <span><a href="#"><fmt:message key="footer.privacy"/></a></span> | 
            <span><a href="#"><fmt:message key="footer.contact"/></a></span>         
		<p>	Copyright &copy; 2008 <a href="http://www.eclipse-sp.com/">Eclipse SP LLC</a> by Java JSP Training team (July 2008) - Best Viewed in <a href="http://www.mozilla.com">Mozilla Firefox</a></p>
	    </div>
    </div>
</div>
</body>
</html>