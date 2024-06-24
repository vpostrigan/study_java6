<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ tag body-content="empty" %>


<%
int time = new java.util.Date().getHours();
if( time >= 5 && time <= 13){
%>
<fmt:message key="welcome.morning" />
<% } %>

<%
if( time > 13 && time < 18){
%>
<fmt:message key="welcome.day" />
<% } %>

<%
if( time >= 18 && time <= 23){
%>
<fmt:message key="welcome.evening" />
<% } %>

<%
if( time >= 23 && time < 5){
%>
<fmt:message key="welcome.night" />
<% } %>


<c:out value="${account.nick}"/>

