<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<jsp:include page="elements/header.jsp"/>

<%
com.eclipsesp.tplanner.core.bean.AccountWithPermission account; 
account = (com.eclipsesp.tplanner.core.bean.AccountWithPermission) request.getSession().getAttribute("account");

if (account.getEmail() != null){
%>
 
	<div id="leftmenu">
 <tags:mainMenu/> 
		<div class="seperator"></div>
	</div>
<% } %>

<div class="form" style="margin-left: 250px; margin-right: 250px;">

</div>



<jsp:include page="elements/footer.jsp"/>