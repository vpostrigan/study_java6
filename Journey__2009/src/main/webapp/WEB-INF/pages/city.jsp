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
<body onload="initialize2()" onunload="GUnload()">
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
					<a class="current"
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

	<div id="subMenu">
		<label for="cities"><fmt:message key="header.jsp.city" />:</label>				
		<label><c:out value="${cityValue.name}" /></label>
	</div>
	
	<div id="typies">
		<c:forEach var="loop" items="${typies}" varStatus="current">
			
			<label>&nbsp;&nbsp;</label>
			<label><a href="<c:url value="/pages/city.jsp">
								<c:param name="city" value="${city}" />
								<c:param name="cityName" value="${cityValue.name}" />
								<c:param name="type" value="${loop.idString}" />
							</c:url>">
           		<c:out value="${loop.name}" />
           	</a></label>
           	
		</c:forEach>
	</div>
	
	<div style="display: none;">
		<table id="tableLocation">
			<c:forEach var="loop" items="${typies}" varStatus="current">
				<c:if test="${typeSelected == loop.idString}">
						<tr>
							<td><c:out value="${cityValue.longitude}"/></td>
							<td><c:out value="${cityValue.latitude}"/></td>
							<td><c:out value="${cityValue.mapZoom}"/></td>
						</tr>
					<c:forEach var="loopT" items="${loop.travelObjects}" varStatus="current">
						<tr>
							<td><c:out value="${loopT.longitude}"/></td>
							<td><c:out value="${loopT.latitude}"/></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</table>
	</div>
	
	
		<div id="map_canvas2" style="float: left; width: 50%; height: 500px;"></div>
		<script>setMapLittleLocationMy('tableLocation');</script>
		<div id="message"></div>
	
	<c:set var="str" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
	<div id="travelObjects">
		<c:forEach var="loop" items="${typies}" varStatus="current">
			<c:if test="${typeSelected == loop.idString}">
			
				<c:forEach var="loopT" items="${loop.travelObjects}" varStatus="currentT">
				<div style="width:100%;">
				<div class="check">
					<input type="checkbox" name="${loop.idString}">
				</div>
			
				<c:set var="tdclass" value="evenRow_" />
				<c:if test="${currentT.count % 2 == 0}">
            		<c:set var="tdclass" value="oddRow_" />
          		</c:if>
				<div class='<c:out value="${tdclass}" />'>
					
						<div>
							<a href="#">
								<c:forEach var="letter" items="${str}" varStatus="letterStatus">
									<c:if test="${currentT.count == letterStatus.count}">
										<c:out value="${letter}" />&nbsp;-&nbsp;<c:out value="${loopT.name}" />
									</c:if>
								</c:forEach>
							</a>
							<br>
						</div>
						<div>
							<label><fmt:message key="travelObject.web_site" />:&nbsp;&nbsp;</label><c:out value="${loopT.web_site}" />
						</div>
						<div>
							<label><fmt:message key="travelObject.telephone" />:&nbsp;&nbsp;</label><c:out value="${loopT.telephone}" />
						</div>
						<div>
							<label><fmt:message key="travelObject.price" />:&nbsp;&nbsp;</label><c:out value="${loopT.price}" />
						</div>
					
				</div>
				</div>
				</c:forEach>
				<!-- <div class="clear"></div> -->
			</c:if>
		</c:forEach>
	</div>
	
	<div class="clear"></div>

<jsp:include page="elements/footer.jsp"/>