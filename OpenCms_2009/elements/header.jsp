<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
	CmsObject cms=jsp.getCmsObject();     
	String uri=jsp.getRequestContext().getUri();
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
	<title>Sales portal</title>
	<link rel="stylesheet" type="text/css" href="<cms:link>../styles/main.css</cms:link>" media="screen">
	<link rel="shortcut icon" type="image/x-icon" href="<cms:link>../images/favicon.ico</cms:link>"> 
	<script src="<cms:link>../scripts/swffix_modified.js</cms:link>" type="text/javascript"></script>
	<%
	 if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "no-cache", true)).getValue())) {
	%>    	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
	<%
	}
	%>
	<cms:editable/>
</head>
<body>
<% boolean homePage=  "/index.html".equals(jsp.getRequestContext().getUri()); %>
<div class="header site_width">
	<div class="top_bg">&nbsp;</div>
    <div class="logo">
    	<% if(!homePage) { %><a href="<cms:link>/index.html</cms:link>"><% } %>
    	<img src="<cms:link>../images/logo.gif</cms:link>" width="306" height="23" border="0">
    	<% if(!homePage) { %></a><% } %>
    </div>
    <%	if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "search.show", true)).getValue()))
    		jsp.include("../elements/search.jsp");
    %>
</div>
<div class="content site_width">
