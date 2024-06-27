<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);

	List list = jsp.getNavigation().getNavigationBreadCrumb();
	Iterator i = list.iterator();

	boolean needChangeTitle	= false ;
	boolean isFolderRoot	= jsp.getCmsObject().readResource (request.getPathInfo()).isFolder() ;

	ArrayList parts = new ArrayList () ;
	while (i.hasNext())
	{
    	org.opencms.jsp.CmsJspNavElement ne = (org.opencms.jsp.CmsJspNavElement)i.next();
    	
		String resTitle = ne.getTitle();
		String href	= jsp.link (ne.getResourceName ()) ;
		if (resTitle == null)
		{
			resTitle = "???" ;
		}
		else if ((resTitle.equals("Sales Portal")))
		{			
				resTitle = "Home";			
		}
		else if ((resTitle.equals("Our Projects")))
		{			
				resTitle = "Projects";	
				href = jsp.link("/projects.html");			
		}
		else if ((resTitle.equals("News")))
		{			
				href = jsp.link("/news.html");			
		}
		else if ((resTitle.equals("Articles")))
		{			
				href = jsp.link("/articles.html");			
		}
		
		if (!isFolderRoot || i.hasNext())
		{
			parts.add ("<a href=\"" + href + "\">" + resTitle + "</a>") ;
		}
	    }
	
	if (!needChangeTitle && jsp.property("Title") != null)
	{
		parts.add (jsp.property("Title")) ;
	}
	
	StringBuffer buffer = new StringBuffer();	
	Iterator iter = parts.iterator();
	String nextTitle=null;
	String currentTitle=null;
	%><div class="breadcrumbs"><%
	while (iter.hasNext())
	{
		currentTitle = nextTitle;
		nextTitle=iter.next().toString();
		if((currentTitle!=null)&&(!((currentTitle.indexOf(nextTitle))>0))) {
		buffer.append(currentTitle);
		//if (iter.hasNext())
		//{
			buffer.append("<img class=\"arrow\" src=\"" + jsp.link("../images/bellet_cr.gif ") + "\" width='7' height='7' alt='' border='0'>");
		//}
		}
	}	
	if(nextTitle!=null)
	buffer.append(nextTitle);	
	out.println (buffer.toString());
%>

	</div>