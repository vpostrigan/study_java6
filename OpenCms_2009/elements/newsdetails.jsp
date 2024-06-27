<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
//${elementProperty.collector}
%>

<cms:include property="template" element="head" />

<div class="news_block">

<cms:editable />

<cms:contentload collector="singleFile" param="%(opencms.uri)" editable="true">	
	<%-- <h1><cms:contentshow element="Title" /></h1> --%>
<%
	if (jsp.getCmsObject().getRequestContext().currentProject().getName().equals("Offline")) {
%>
	<p><small><cms:contentshow element="Author" /></small></p>
<%
	}
%>
	<div class="one_news">
	<div class="news_date">			
				<cms:contentcheck ifexists="Release">
					<c:set var="dateString">
						<cms:contentshow element="Release" />
					</c:set>
					<jsp:useBean id="date" class="java.util.Date" />
					<% date.setTime(new Long((String)pageContext.getAttribute("dateString")).longValue()); %>
					<% java.text.DateFormat dateformat = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG, jsp.getRequestContext().getLocale());%>
					<%=dateformat.format(date) %>
				</cms:contentcheck>				
			</div>	
    <div class="news_anounce full">
    </div>
    <div class="news_txt">
    	<cms:contentshow element="Text" />
    </div>
    </div>
</cms:contentload>
</div>
<br/>

<cms:include property="template" element="foot" />