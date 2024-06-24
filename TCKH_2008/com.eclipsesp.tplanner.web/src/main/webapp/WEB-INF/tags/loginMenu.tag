<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ tag body-content="empty" %>

<!-- Method for checking whether the user is logged in or no -->

<%
com.eclipsesp.tplanner.core.bean.AccountWithPermission account; 
account = (com.eclipsesp.tplanner.core.bean.AccountWithPermission) request.getSession().getAttribute("account");

if (account.getPermission() == 0){
%>
<span><a href="../pages/login.html"><fmt:message key="login.signIn" /></a></span> | <span><a href="../pages/registration.html"><fmt:message key="login.signUp" /></a></span>
<% 
} else{
%>
<span><a href="../pages/logout.html"><fmt:message key="login.signOut" /></a></span> | <span><a href="../pages/registration.html"><fmt:message key="login.signUp" /></a></span>
<%} %>