<%--
/* *****************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * Home page
 * 
 * $Revision: 889 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-14 14:26:42 +0300 (Чт, 14 авг 2008) $
 * ****************************************************************************/
--%>
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

<div class="seperator">
	        </div>
        	   
        </div>
        <div id="content">    
	
		        <form:form name="addTF" action="tournaments.html" method="post" commandName="tournaments" modelAttribute="tournamentView">
		
	<table class="sortable" id="sortabletable" width=100%  border=1 cellpadding=3 cellspacing=0>
		<tr>
   	  		<th id="noImg" class="tableHeader_">&nbsp;</th>	
      		<th class="tableHeader_"><fmt:message key="tournament.gameKind" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.owner" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.name" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.startDate" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.finishDate" /></th>
      		<th class="tableHeader_"><fmt:message key="tournament.status" /></th>      		
      		<th class="tableHeader_"><fmt:message key="tournament.description" /></th>
   		</tr>
   	<c:forEach items="${tournaments}" var="tournament" varStatus="current">
   	
   		<c:set var="tdclass" value="oddRow_" />
		<c:if test="${current.count % 2 == 0}">
           	<c:set var="tdclass" value="evenRow_" />
        </c:if>
   	
      	<tr>
          	<td class='<c:out value="${tdclass}" />'>
          		<input type="radio" name="id" value="${tournament.id}"/>
          	</td>
         	<td class='<c:out value="${tdclass}" />'>
         		<c:out value="${tournament.gameKind}"/>
         	</td>
          	<td class='<c:out value="${tdclass}" />'>
          		<c:out value="${tournament.owner}"/>
          	</td>
          	<td class='<c:out value="${tdclass}" />'>
          		<c:out value="${tournament.tournamentName}"/>
          	</td>    
          	<td class='<c:out value="${tdclass}" />'>
          		<fmt:formatDate value="${tournament.startDate}" type="date" dateStyle="medium"/>
          	</td>  
          	<td class='<c:out value="${tdclass}" />'>
          		<fmt:formatDate value="${tournament.finishDate}" type="date" dateStyle="medium"/>
          	</td>  
          	<td class='<c:out value="${tdclass}" />'>
          		<c:out value="${tournament.status}"/>
          	</td>               
          	<td class='<c:out value="${tdclass}" />'>
          		<c:out value="${tournament.description}"/>
          	</td>   
     	</tr>
    </c:forEach>      		
	</table>
	<c:if test="${empty tournaments}">
      	    <p class="errorLow_">
          	     <fmt:message key="error.noRecords"/>
       	   </p>
   	  </c:if>   
	<br/>
	
	<button class="send_button3" type="submit"  name="action" value="Add" onclick="this.form.submit();">Add</button> 
	<button class="send_button3" type="submit"  name="action" value="Edit" onclick="this.form.submit();">Edit</button>
	<button class="send_button3" type="submit"  name="action" value="Delete" onclick="return confirm('Are you sure?');this.form.submit();">Delete</button>
	<button class="send_button3" type="submit"  name="action" value="Invite" onclick="this.form.submit();">Invite</button>
	<button class="send_button3" type="submit"  name="action" value="Start" onclick="return confirm('Are you sure?');this.form.submit();">Start</button>
	<button class="send_button3" type="submit"  name="action" value="Finish" onclick="return confirm('Are you sure?');this.form.submit();">Finish</button>
	<button class="send_button3" type="submit"  name="action" value="Details" onclick="this.form.submit();">Details</button>
	<button class="send_button3" type="submit"  name="action" value="Join" onclick="return confirm('Are you sure?');this.form.submit();">Join</button>
	
	<br/><br/><br/><br/><br/>
	
	<form:errors path="*" cssClass="errorMessage"/>	
	
	
		
</form:form>	
			 </div>
		<div class="clear">
	</div>	

<jsp:include page="elements/footer.jsp"/>