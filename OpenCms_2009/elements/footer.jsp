<%@page buffer="none" session="true"
	import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
%>
<jsp:useBean id="currentData" class="java.util.Date" />

<div class="footer site_width">
<table border="0" style="width: 100%">
	<tr>
		<td style="text-align: left">Copyright &copy; 1997&#8212;<fmt:formatDate
			pattern="yyyy" value="${currentData}" /> SP LLC. <br>
		All rights reserved.</td>
		<% if(!jsp.getRequestContext().currentUser().isGuestUser()) 
    	{
    		String name=jsp.getRequestContext().currentUser().getFirstname()+" "+ jsp.getRequestContext().currentUser().getLastname() ;
    		if((name!=null)&&(!(name.trim().equals("")))) name+=" | ";
    	%>
		<td style="text-align: right; vertical-align: top"><b><%= name %>
		<% if(!"Guest | ".equals(name)) { %> <a
			href="<cms:link>/login.html?action=change&requestedResource=<%= jsp.getRequestContext().getUri() %></cms:link>"
			style="color: #6666FF; font-weight: bold; text-decoration: none">Change
		password</a> &nbsp;|&nbsp; <% } %> <a
			href="<cms:link>/login.html?action=logout</cms:link>"
			style="color: #6666FF; font-weight: bold; text-decoration: none">Exit</a>
		</b></td>
		<% } %>
	</tr>
</table>
</div>
</div>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script>
<script type="text/javascript">
_uacct = "UA-4595397-2";
urchinTracker();
</script>
</body>
</html>
