<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page buffer="none" session="true" import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
	org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*, java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%! 
	public static boolean checkJSinjection(Collection<String> values){
	
		final Pattern[] REGEX_MATCH_SCRIPT_PATTERNS = { 
				Pattern.compile(".*<.*[sS].*[cC].*[rR].*[iI].*[pP].*[tT].*>.*"),
				Pattern.compile(".*[jJ].*[aA].*[vV].*[aA].*[sS].*[cC].*[rR].*[iI].*[pP].*[tT].*:.*")
			};
		
		for(Pattern pattern : REGEX_MATCH_SCRIPT_PATTERNS){
			for(String value : values){
				Matcher matcher = pattern.matcher(value);
				if(matcher.matches()){
					return true;
				}
			}
		}
		
		return false;
	}
%>

<cms:template element="head">
	<%
		CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
		CmsObject cms = jsp.getCmsObject();     
		String uri = jsp.getRequestContext().getUri();
		
		
		//Collection<String> valuesForJSinjectionCheck = new ArrayList<String>();
		
		//Enumeration paramNames = jsp.getRequest().getParameterNames();
		//while(paramNames.hasMoreElements()){
		//	String paramName = (String) paramNames.nextElement();
		//	String paramValue = jsp.getRequest().getParameter(paramName);
			
		//	valuesForJSinjectionCheck.add(URLDecoder.decode(paramName, "UTF-8"));
		//	valuesForJSinjectionCheck.add(URLDecoder.decode(paramValue, "UTF-8"));
		//}
		
		boolean injection = false/*checkJSinjection(valuesForJSinjectionCheck)*/;
		pageContext.setAttribute("injectionInParams", injection);
		
		jsp.include("../elements/header.jsp");
	%>
	
	<c:choose>
		<c:when test="${injectionInParams == 'true'}">
			<div class="left_area"></div>
			<div class="right_area"><%
				out.println("Request has potential danger for server. " +  
						"You cannot use words <b>&lt;script&gt;</b> or <b>javascript</b><br>" +
						"Contanct with administrator " + 
						"<a href=\"mailto:sales@sp.com\">sales@sp.com</a><br>");
				out.println("Continue your navigation from the:");
				jsp.include("../elements/generalError.jsp");
			%><div class="txt">
		</c:when>
		<c:otherwise>
			<div class="left_area"><%
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "menu.show", true)).getValue()))
			    	jsp.include("../elements/menu.jsp");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "articles.show.others", true)).getValue()))
			    	jsp.include("../elements/articles.jsp", "menu");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "projects.show.others", true)).getValue()))
			    	jsp.include("../elements/projects.jsp", "menu");
		    %></div><%
		    
		    %><div class="right_area"><%
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "flash.show", true)).getValue()))
			    	jsp.include("../elements/flash.jsp");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "breadcrumb.show", true)).getValue())) {
			    	jsp.include("../elements/breadcrumb.jsp");
			    }
			    
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "title.show", true)).getValue())) { %>
			    	<h1><%= ((CmsProperty)cms.readPropertyObject(uri, "Title", false)).getValue() %></h1>
			    <% } %>
			<div class="txt"><%		   
			    if("/login.html".equals(uri)) {
			    	jsp.include("../elements/login.jsp");
			    }
			    
			    CmsJspTagInclude.includeTagAction(pageContext, null, "body", true, null, request, response);
			    
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "projects.show", true)).getValue()))
			    	jsp.include("../elements/projects.jsp", "list");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "news.show", true)).getValue()))
			    	jsp.include("../elements/news.jsp");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "articles.show", true)).getValue()))
			    	jsp.include("../elements/articles.jsp", "list");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "search.result", true)).getValue()))
			    	jsp.include("../elements/found.jsp");
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "403.show", true)).getValue()))
			    	jsp.include("../elements/403.jsp"); // 403 error page
			    if("true".equals(((CmsProperty)cms.readPropertyObject(uri, "generalError.show", true)).getValue()))
			    	jsp.include("../elements/generalError.jsp"); // for all error pages, except 403 page
			%>
		</c:otherwise>
	</c:choose>
</cms:template>

<cms:template element="foot">
    </div></div>
<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
	jsp.include("../elements/footer.jsp");
%>
</cms:template>