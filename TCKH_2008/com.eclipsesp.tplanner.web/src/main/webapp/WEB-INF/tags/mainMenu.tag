<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ tag body-content="empty" %>

<!-- Tag for displaying the main menu -->

<%
com.eclipsesp.tplanner.core.bean.AccountWithPermission account; 
account = (com.eclipsesp.tplanner.core.bean.AccountWithPermission) request.getSession().getAttribute("account");

if (account.getPermission() == 0){}
else{
%>

<a href="../pages/tournaments.jsp?ownerId= <%= account.getId() %>" > <fmt:message key="mainmenu.mytournaments"/> </a><br/>
<a href="../pages/myinvites.jsp"> <fmt:message key="mainmenu.myinvites"/>(<strong><%= request.getSession().getAttribute("invitescount") %></strong>)</a><br/>
<a href="#"> <fmt:message key="mainmenu.myschedule"/> </a><br/>        
<a href="/com.eclipsesp.tplanner.web/pages/editAccount.html"> <fmt:message key="mainmenu.mydetails"/> </a><br/>

<% } %>	