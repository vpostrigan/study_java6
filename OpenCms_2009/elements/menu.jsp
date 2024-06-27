<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*,
org.opencms.main.*,org.opencms.jsp.*,org.opencms.jsp.util.*,java.util.regex.*"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);

	// Get a simple navigation of all pages / subfolders in the current folder
	List list = jsp.getNavigation().getNavigationForFolder();
	int level = 0;
	String parentFolder = "";
	boolean emptyIndex = true;

	// Get reference navigation item and gathering data
	if (list.size() > 0) 
	{
		CmsJspNavElement RefNavElement = (CmsJspNavElement)list.get(0);
		level = RefNavElement.getNavTreeLevel();
		parentFolder = RefNavElement.getParentFolderName();
		emptyIndex = false;
	}

	String rootFolder = "/";

	Iterator i = list.iterator();
	String filename = jsp.getRequestContext().getUri();
%>
		
    <div class="menu">
    	<ul>
	
<%
	if (level == 0 && rootFolder.equals(parentFolder))
	{
		while (i.hasNext())
		{
			CmsJspNavElement ne = (CmsJspNavElement)i.next();
			if(ne.getResourceName().equals(filename))
				out.println("<li>" + ne.getNavText() + "</li>");
			else
				out.println("<li><a href=\"" + jsp.link(ne.getResourceName()) + "\">" + ne.getNavText() + "</a></li>");		
		}
	}	
	else if (emptyIndex || (level == 1))
	{
		list = jsp.getNavigation().getNavigationForFolder(rootFolder);
		i = list.iterator();
		while (i.hasNext())
		{
			CmsJspNavElement ne = (CmsJspNavElement)i.next();
			if (parentFolder.equals(ne.getResourceName()))
			{				
				if(filename.equals(ne.getResourceName()+"index.html") && ne.isFolderLink())
				    out.println("<li>"+ne.getNavText() + "</li>");
				else
				    out.println("<li><a href=\""+jsp.link(ne.getResourceName())+"\">"+ne.getNavText() + "</a></li>");			
				List subList = jsp.getNavigation().getNavigationForFolder(parentFolder);
				Iterator i2 = subList.iterator();
				 out.println("<dl><dt>");
				while (i2.hasNext())
				{
					CmsJspNavElement sne = (CmsJspNavElement)i2.next();
					if (!sne.getResourceName().contains("index"))
					{						
						if(sne.getResourceName().equals(filename))
					     	out.println("<dd><b>"+sne.getNavText()+"</b></dd>");
					else		
					     	out.println("<dd><a href=\""+jsp.link(sne.getResourceName())+"\">"+sne.getNavText()+"</a></dd>");						
					}
				}
				out.println("</dt></dl>");
			}
			else
			{
				out.println ("<li><a href=\"" + jsp.link(ne.getResourceName()) + "\">"+ne.getNavText() + "</a></li>");
			}			
		}
	} 
%>

        	</ul>
        </div>
        