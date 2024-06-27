<%@page buffer="none" session="true"
	import="org.opencms.file.*,org.opencms.jsp.*,org.opencms.xml.*,java.util.*,org.opencms.i18n.*,
	org.opencms.main.*,org.opencms.jsp.util.*,java.util.regex.*,org.opencms.util.*,java.text.*,
	org.opencms.frontend.templateone.modules.*,java.io.*,org.opencms.loader.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="cmsbean" class="org.opencms.jsp.CmsJspBean">
	<%
		cmsbean.init(pageContext, request, response);
	%>
</jsp:useBean>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
	String uri = jsp.getRequestContext().getUri();
	CmsObject cms = jsp.getCmsObject();

	// We will need the resource type int value
	//org.opencms.file.types.I_CmsResourceType resourceType = OpenCms.getResourceManager().getResourceType("ecs_event");

	String pageSize = "5";
	try {
		pageSize = Integer.valueOf(((CmsProperty) cms.readPropertyObject(uri, "news.count", true)).getValue()).toString();
	} catch (Exception e) {
		pageSize = "5";
	}

	int pageIndexTemp = 1;
	int pages = 1;
	boolean zeroArticles = false;
	try {
		pageIndexTemp = Integer.valueOf(request.getParameter("page"));
	} catch (Exception e) {
		pageIndexTemp = 1;
	}
	try {
		int eventsCount = cmsbean.getCmsObject().getFilesInFolder("/news/"/*, CmsResourceFilter.requireType(resourceType.getTypeId())*/).size();
		
		if (eventsCount >= 1) {
			if (pageIndexTemp > 1) {
				float pageCount = eventsCount / Integer.valueOf(pageSize);
				float pageCountFractionalPart = eventsCount % Integer.valueOf(pageSize);

				if (pageCountFractionalPart > 0.0) {
					pageCount += 1.0;
				}

				pages = (int) pageCount;
			}

			if (pageIndexTemp < 1) {
				// ex., want to open page 0 (-1)
				pageIndexTemp = 1;
			}

			if (pageIndexTemp > pages) {
				// ex., want to open page 8 but system have only 7
				pageIndexTemp = pages;
			}
		} else {
			// in folder zero files
			zeroArticles = true;
		}
	} catch (Exception e) {
		pageIndexTemp = 1;
	}
	// Page index
	String pageIndex = "" + pageIndexTemp;
	pageContext.setAttribute("zeroArticles", zeroArticles);
%>
<div class="news_block">
	<c:if test="${zeroArticles == 'true'}">
		<b>There are no values.</b>
	</c:if>
	<c:if test="${zeroArticles == 'false'}">
		<c:catch var="errorOnLoad">
			<cms:contentload collector="allInFolderDateReleasedDesc"
				param="news/article_%(number).html|article" editable="true"
				pageSize="<%= pageSize %>" pageIndex="<%= pageIndex %>" pageNavLength="10">
		
				<cms:contentinfo var="info" scope="page" />
				<div class="one_news">
				<div class="news_date">
					<cms:contentcheck ifexists="Release">
						<c:set var="dateString">
							<cms:contentshow element="Release" />
						</c:set>
						<jsp:useBean id="date" class="java.util.Date" />
						<%
							date.setTime(new Long((String) pageContext
												.getAttribute("dateString")).longValue());
						%>
						<%
							java.text.DateFormat dateformat = 
								java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG, 
										cms.getRequestContext().getLocale());
						%>
						<%= dateformat.format(date) %>
					</cms:contentcheck>
				</div>
				<div class="news_link"><a
					href="<cms:link><cms:contentshow element="%(opencms.filename)" /></cms:link>"><cms:contentshow
					element="Title" /></a></div>
				<div class="news_anounce"><cms:contentshow element="Teaser" /></div>
				</div>
			</cms:contentload>
		</c:catch> 
		<c:if test="${not empty errorOnLoad}">
			<b>Error during news loading</b>
		</c:if>
	</c:if>
</div>
<c:choose>
	<c:when test="${info.pageNavEndIndex != 1}">
		<%
			if ("true".equals(((CmsProperty) cms.readPropertyObject(uri, "news.paging", true)).getValue())) {
		%>
		<div class="paginatior">
		<div class="pg_left">Please find Early News<img class="arrow"
			src="<cms:link>../images/bellet_cr.gif</cms:link>" width="7"
			height="7"></div>
		<div class="pg_right">
			<ul>
				<c:forEach var="i" begin="${info.pageNavStartIndex}" end="${info.pageNavEndIndex}">
					<c:choose>
						<c:when test="${info.pageIndex==i}">
							<li><c:out value="${i}" /></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="<cms:link><cms:info property="opencms.request.uri" /><c:out value="?page=${i}"/></cms:link>"><c:out
								value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		</div>
		<%
			}
		%>
	</c:when>
</c:choose>