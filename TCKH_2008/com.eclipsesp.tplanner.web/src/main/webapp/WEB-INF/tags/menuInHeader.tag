<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ tag body-content="empty" %>

<!-- Tag for displaying the menu in header -->


<%
com.eclipsesp.tplanner.web.form.PageCC page = null;

if(request.getAttribute("pageid") == null){
	page = new com.eclipsesp.tplanner.web.form.PageCC();
	page.setPageId("Nothing");
}else{
	page = (com.eclipsesp.tplanner.web.form.PageCC)request.getAttribute("pageid") ;
}
%>

<% if(page.getPageId() == null) {} %>

<% 
int permission = (Integer)request.getSession().getAttribute("permission");
%>

<%
if(com.eclipsesp.tplanner.core.security.Security.checkPermissionMask(permission, com.eclipsesp.tplanner.core.security.Security.compileSecurityMask(com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_NOTHING,com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_NOTHING) )){
if(page.getPageId() == "news"){
%>
	           <li><span><a href="../pages/news.jsp" ><fmt:message key="menu.news" /></a></span></li>
<%	
}
else {
%>
   <li><a href="../pages/news.jsp" ><fmt:message key="menu.news" /></a></li>
<%
}};
%>



<%
if(com.eclipsesp.tplanner.core.security.Security.checkPermissionMask(permission, com.eclipsesp.tplanner.core.security.Security.compileSecurityMask(com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_READ_ALL,com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_NOTHING) )){
if(page.getPageId() == "users"){
%>
	           <li><span><a href="../pages/users.jsp" ><fmt:message key="menu.users" /></a></span></li>
<%	
}
else {
%>
   <li><a href="../pages/users.jsp" ><fmt:message key="menu.users" /></a></li>
<%
}};
%>


        
<% 
if(com.eclipsesp.tplanner.core.security.Security.checkPermissionMask(permission, com.eclipsesp.tplanner.core.security.Security.compileSecurityMask(com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_NOTHING,com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_READ_ALL) )){
if(page.getPageId() == "tournaments"){
%>
<li><span><a href="../pages/tournaments" ><fmt:message key="menu.tournaments" /></a></span></,li>
<%	
}
else {
%>
	           <li><a href="../pages/tournaments" ><fmt:message key="menu.tournaments" /></a></li>
<%
}
};
%>    


<%
if(com.eclipsesp.tplanner.core.security.Security.checkPermissionMask(permission, com.eclipsesp.tplanner.core.security.Security.compileSecurityMask(com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_WRITE_ALL,com.eclipsesp.tplanner.core.security.utils.SingleMask.CAN_WRITE_ALL) )){
if(page.getPageId() == "releasenotes"){
%>
	           <li><span><a href="./releasenotes.jsp" ><fmt:message key="menu.releasenotes" /></a></span></li>
<%	
}
else {
%>
   <li><a href="./releasenotes.jsp" ><fmt:message key="menu.releasenotes" /></a></li>
<%
}};
%>