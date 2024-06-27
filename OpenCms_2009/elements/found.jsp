<%@page buffer="none" session="true" %>
<%@page import="org.opencms.file.*,org.opencms.jsp.*,org.opencms.xml.*,java.util.*,
org.opencms.i18n.*, org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,
java.util.regex.*,org.apache.commons.lang.StringEscapeUtils" %>
<%@page import="org.opencms.search.CmsSearchManager"%>
<%@page import="org.opencms.search.CmsSearchResult"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);

	String captSearchResult = "Found %d results for <strong>%s</strong>"; 
	// String captSearchChanged = "Changed";
	
	// note: here, searchQuery is checked. But if it passes the check, search
	// will obtain the query from the request again (without using searchQuery).
	String searchQuery = request.getParameter("query");	
	searchQuery = URLDecoder.decode(searchQuery, "UTF-8");
	
	if (searchQuery == null) searchQuery = "";
	searchQuery = searchQuery.trim();
	
	if (searchQuery == "") {
		out.println("<p>Search query is empty. Please specify the search query and try again.</p>");
	} else {
		// trying to search & output results
		try {
	    	// Get the search manager
	    	CmsSearchManager searchManager = OpenCms.getSearchManager(); 
			// java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd MMM yyyy", jsp.getRequestContext().getLocale());
%>
<jsp:useBean id="search" scope="request" class="org.opencms.search.CmsSearch">
	<jsp:setProperty name = "search" property="*"/>
	<%
			search.init(jsp.getCmsObject());
	%>
</jsp:useBean>
<%
		int resultno = 1;
		int pageno = 0;
		if (request.getParameter("searchPage") != null){
			pageno = Integer.parseInt(request.getParameter("searchPage")) - 1;
		}
		resultno = (pageno*search.getMatchesPerPage()) + 1;
		
		String fields = search.getFields();
		if (fields==null) fields = request.getParameter("fields");
		
		List result = search.getSearchResult();
		
		// for testing purposes: re-throw to handle the actual exception instead of NullPointerException
		if ((result == null) && (search.getLastException() != null)) throw search.getLastException();
		
		ListIterator iterator = result.listIterator();
		Matcher matcher = Pattern.compile("[^ \t\r\n]+").matcher(search.getQuery());
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String word=matcher.group();
			StringBuffer result2 = new StringBuffer();
			result2.setLength(0);
			for (int i = 0; i < word.length() / 5; i++) {
				if (i != 0) result2.append("<wbr>&shy;");
				result2.append(StringEscapeUtils.escapeHtml(word.substring(i * 5, (i + 1) * 5)));
			}
			if (word.length() % 5 != 0) {
				if (result2.length() > 0) result2.append("<wbr>&shy;");
				result2.append(StringEscapeUtils.escapeHtml(word.substring((word.length() / 5) * 5)));
			}
			String result3=result2.toString();
			matcher.appendReplacement(sb, result3.replaceAll("\\$","\\\\\\$"));
		}
		matcher.appendTail(sb);
		String queryText=sb.toString().replaceAll("\r", "").replaceAll("\n", "<br>");
%>
<div class="results_count"><%=String.format (captSearchResult, new Object[] {new Integer(search.getSearchResultCount() ), queryText}) %></div>
<!--[if IE]><div style="padding-left:4px "><![endif]-->
<ol start="<%= resultno %>" style="counter-reset: item <%= resultno %>" >
<%
			// results output
			while (iterator.hasNext()) {
				CmsSearchResult entry = (CmsSearchResult)iterator.next();
%>
<li>
<div class="one_result">
<div class="one_result_link"><a href="<%= jsp.link(jsp.getRequestContext().removeSiteRoot(entry.getPath())) %>"><%= entry.getTitle() %></a>&nbsp;(<%= entry.getScore() %>%)</div>
<div class="sh_desc"><%= entry.getExcerpt() %></div>
<%--
<%= captSearchChanged %>:
<%= formatter.format(entry.getDateLastModified()) %>
--%>
</div>
</li>   
<%
				resultno++; 
			} // results output
%>
</ol>
<!--[if IE]></div><![endif]-->
<%
			Map pageLinks = search.getPageLinks();
			Iterator i2 =  pageLinks.keySet().iterator();
			if (i2.hasNext()) {
%>
<div class="paginatior">
<div class="pg_left">Please find more results<img class="arrow" src="<cms:link>../images/bellet_cr.gif</cms:link>" width="7" height="7"></div>
<div class="pg_right">
<ul>
<%
				// page numbers output
				while (i2.hasNext()) {
					int pageNumber = ((Integer)i2.next()).intValue();
					
					String s = (String)pageLinks.get(new Integer(pageNumber));
					int j = s.indexOf("query=");
					int k = s.indexOf("&matchesPerPage=", j);
					
					// Encode string with URL encoder
					String encode = URLEncoder.encode(s.substring(j+6, k), "UTF-8");
					
					String pageLink = jsp.link(
							s.substring(0, j) + "query=" + encode + s.substring(k, s.length()));       		
					if (pageNumber != search.getSearchPage()) {
%>
<li><a href="<%= pageLink %>&fields=<%= fields %>"><%= pageNumber %></a></li>
<%
					} else {
%>
<li><%= pageNumber %></li>
<%
					}
				} // page numbers output
%>
</ul>
</div>   
</div>
<%
			} // if (i2.hasNext())
		} catch (Exception exception) {
			// failed to search or output results
			out.println("<p>Sorry, search parameters are incorrect. Please check the search query and try again.</p>");
			// for testing purposes only! do not uncomment!
			// out.println(exception.toString());
		}
	} // searchQuery != ""
%>
