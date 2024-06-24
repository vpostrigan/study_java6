<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/tplanner.tld" prefix="tplanner"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<jsp:include page="elements/header.jsp"/>

			
			
	
			<div id="leftmenu">
			  <tags:mainMenu/>
<%--		
			<a href='tournaments.jsp?ownerId=<c:out value="${account.id}"/>'>My Tournaments</a><br/>
			<a href="#">My Invites (<strong>0</strong>)</a><br/>
			<a href="#">My Schedule</a><br/>        
			<a href="#">My Details</a><br/>	
--%>
<div class="seperator">
	        </div>
        	   
        </div>
        <div id="content">   
	
		        <form:form name="addTF" action="myinvites.html" method="post" commandName="invites" modelAttribute="invites">
		
	<table class="sortable" id="sortabletable" width=100% border=1 cellpadding=3 cellspacing=0>
		<tr>
      		<th class="tableHeader_"><fmt:message key="tournament.name" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.status" /></th>
      		<th class="tableHeader_">&nbsp;</th>
      		<th class="tableHeader_">&nbsp;</th>
   		</tr>
   	<c:forEach items="${invites}" var="invite" varStatus="current">
   	
   		<c:set var="tdclass" value="evenRow_" />
		<c:if test="${current.count % 2 == 0}">
           	<c:set var="tdclass" value="oddRow_" />
        </c:if>
   	
      	<tr>
         	<td class='<c:out value="${tdclass}" />'>
         		<c:out value="${invite.tournamentName}"/>
         	</td>
          	<td class='<c:out value="${tdclass}" />'>
          		<c:out value="${invite.statusName}"/>
          	</td>
          	<td class='<c:out value="${tdclass}" />'>
          		<c:if test="${invite.status eq 4}">
          			<a href="myinvites.html?id=${invite.tournamentid}&action=accept"><fmt:message key="invites.accept"/></a>
          		</c:if>
          		&nbsp;	
          	</td>
          	<td class='<c:out value="${tdclass}" />'>
          		<c:if test="${invite.status eq 4}">
          		<a href="myinvites.html?id=${invite.tournamentid}&action=reject"><fmt:message key="invites.reject"/></a>
          		</c:if>
          		&nbsp;	
          	</td>
     	</tr>
    </c:forEach>      
	</table>
	<c:if test="${empty invites}">
		<p class="errorLow_">
			<fmt:message key="error.noRecords"/>
		</p>
	</c:if>	
	<br/>
	
	<form:errors path="*" cssClass="errorMessage"/>	
	<br/>	
		
</form:form>	
			 </div>
		<div class="clear">
	</div>	

<jsp:include page="elements/footer.jsp"/>