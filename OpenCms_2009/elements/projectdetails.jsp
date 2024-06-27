<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="string" uri="http://jakarta.apache.org/taglibs/string-1.1" %> 

<%
	CmsJspActionElement cms = new CmsJspActionElement(pageContext, request, response);
%>

<cms:include property="template" element="head" />


<cms:editable />

	<table class="project_resume" border="0" cellpadding="3" cellspacing="0">
	<cms:contentload collector="singleFile" param="%(opencms.uri)" locale="en">
		<cms:contentloop element="PrParameter">
		 <tr>
                <td class="resume_th">       
			<string:replace replace=":" with="</td><td class=\"resume_desc\" style=\"padding-left:20px\" >">
				<cms:contentshow />
			</string:replace>&nbsp;
			</td>
		</tr>
		</cms:contentloop>
	</cms:contentload>
	</table>

	
	<cms:contentload collector="singleFile" param="%(opencms.uri)" locale="en">
		<c:set var="showDescription" value="false"/>
		<c:set var="prTextExists" value="false"/>
		

		<cms:contentcheck ifexists="PrText">
			<c:set var="prTextExists" value="true"/>
		</cms:contentcheck>

		<c:if test="${prTextExists == 'true'}">
			<cms:contentshow element="PrText" />
		</c:if>
	</cms:contentload>


<cms:include property="template" element="foot" />



