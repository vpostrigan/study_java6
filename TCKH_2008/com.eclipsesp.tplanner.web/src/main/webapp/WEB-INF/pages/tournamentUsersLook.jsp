<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

	<!-- tournamentUserLook.jsp -->

<jsp:include page="elements/header.jsp"/>

<div id="content">
<c:if test="${not empty tournamentId && tournamentId > 0}">
	<h2>
		<fmt:message key="tournamentUsers.userOfTournament"/>: <b><c:out value="${tournamentName}" /></b>
	</h2><br />	

	<div class="blockRight_">
		<img src="../images/find.gif" alt="" border="0" align="top" />
		<input id="searchId1" type="text" onkeyup="return searchInTable('searchId1', 'usersTournament');">
	</div>
</c:if>
	
<c:if test="${not empty tournamentId && tournamentId > 0}">		
	<table id="usersTournament" width=100% border=1 cellpadding=3 cellspacing=0>
		<tr>
       		<th class="tableHeader_"> <fmt:message key="users.nick"/> </th>
         	<th class="tableHeader_"> <fmt:message key="users.firstName"/> </th>
            <th class="tableHeader_"> <fmt:message key="users.lastName"/> </th>
            <th class="tableHeader_"> <fmt:message key="registration.address"/> </th>            	
		</tr>
		
    	<c:forEach var="loop" items="${usersTournament}" varStatus="current">
					
			<c:set var="tdclass" value="evenRow_" />
			<c:if test="${current.count % 2 == 0}">
            	<c:set var="tdclass" value="oddRow_" />
          	</c:if>
            
            <tr>           			
           		<td class='<c:out value="${tdclass}" />'>
           			<a class="menu_" href="account.jsp?id=<c:out value='${loop.id}'/>">
           				<c:out value="${loop.nick}" escapeXml="false"/>
           			</a>
            	</td>
            	<td class='<c:out value="${tdclass}" />'>
            		<c:out value="${loop.firstName}" escapeXml="false"/>
            	</td>
            	<td class='<c:out value="${tdclass}" />'>
            		<c:out value="${loop.lastName}" escapeXml="false"/>
            	</td>
            	<td class='<c:out value="${tdclass}" />'>
            		<c:out value="${loop.description}" escapeXml="false"/>
            	</td>
        	</tr>
		</c:forEach> 		
	</table>
</c:if>

<c:if test="${empty usersTournament}">
	<p class="errorLow_">
		<fmt:message key="error.noRecords"/>
	</p>
</c:if>

<br />
</div>

<jsp:include page="elements/footer.jsp"/>