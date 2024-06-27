<%@page buffer="none" session="true"
	import="org.opencms.file.*,org.opencms.jsp.*,org.opencms.xml.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
	String uri=jsp.getRequestContext().getUri();
	CmsObject cms=jsp.getCmsObject(); 
	String filename = jsp.getRequestContext().getUri();
	boolean linkSelected=  "true".equals(((CmsProperty)cms.readPropertyObject(uri, "link.selected.project", true)).getValue());
%>
<cms:template element="list">
<ul>
	<cms:contentload collector="allInFolderNavPos" param="projects/project_%(number).html|project" editable="true">
	<li><a href="<cms:link><cms:contentshow element="%(opencms.filename)" /></cms:link>"><cms:contentshow element="Title" /></a></li>
	</cms:contentload>
</ul>
</cms:template>
<cms:template element="menu">
<div class="project_list left">
	<h1>More projects</h1>
	<dl>
		<cms:contentload collector="allInFolderNavPos" param="projects/project_%(number).html|project" editable="true">
		<c:set var="filename" scope="page" >
		<cms:contentshow element="%(opencms.filename)" />
		</c:set>
		<% if((!filename.equals(pageContext.getAttribute("filename")))||(linkSelected)) { %>
		<dt><p class="project_link_left" style="margin-bottom: 5px;" ><a href="<cms:link><cms:contentshow element="%(opencms.filename)" /></cms:link>"><cms:contentshow element="Title" /></a></p></dt>
		<% } else { %>
		<dt><p class="project_link_left" style="margin-bottom: 5px;" ><cms:contentshow element="Title" /></p></dt>
		<% } %>
		</cms:contentload>
	</dl>
</div>
</cms:template>
	
