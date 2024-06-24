<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

	<!-- tournamentUsers.jsp -->

<jsp:include page="elements/header.jsp"/>
	
	
	<div id="leftmenu"> 
   		<tags:mainMenu/>
    </div>
	
	
	<div id="content">
	<table width=100% border=0>
		<tr>
			<td>&nbsp;</td>
			<td width="*">&nbsp;</td>
			<td align="right">
				<a href="tournaments.jsp">
					<fmt:message key="tournamentUsers.listOfTournaments"/>
           		</a>
			</td>
		</tr>
	</table>
	<br />
	
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
	
		<form:form name="addUser" action="tournamentUsers.jsp" method="post" 
							commandName="tournaments" modelAttribute="tournaments">
		
		<table id="usersTournament" width=100% border=1 cellpadding=3 cellspacing=0>
			<tr>
            	<th class="tableHeader_"> <fmt:message key="users.nick"/> </th>
            	<th class="tableHeader_"> <fmt:message key="users.firstName"/> </th>
            	<th class="tableHeader_"> <fmt:message key="users.lastName"/> </th>
            	<th class="tableHeader_"> <fmt:message key="registration.address"/> </th>
            	<th class="tableHeader_"> </th>
			</tr>
		
    		<c:forEach var="loop" items="${usersTournament}" varStatus="current">
					
				<c:set var="tdclass" value="evenRow_" />
				<c:if test="${current.count % 2 == 0}">
            		<c:set var="tdclass" value="oddRow_" />
          		</c:if>
            
            	<tr>           			
           			<td class='<c:out value="${tdclass}" />'>
           				<a class="menu_" href="account.jsp?id=<c:out value='${loop.id}'/>">
           					<c:out value="${loop.nick}" escapeXml="true"/>
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
            		<td class='<c:out value="${tdclass}" />'>
            			<div class="blockCenter_">
            				<form:checkbox path="id" value="${loop.id}"/> 		
            			</div>            			
            		</td>
        		</tr>
			</c:forEach> 		
		</table>
		
		<input type="hidden" name="tournamentId" value="${tournamentId}"/>
		
		<c:if test="${not empty tournamentId && tournamentId > 0 && not empty usersTournament}">	
			<br />
			<div class="blockRight_">
				<button class="send_button2" type="submit" name="action" value="Delete" onclick="this.form.submit();">
					<fmt:message key="button.Delete"/>
				</button>
				<img src="../images/down.gif" alt="" border="0" align="middle" />
			</div> 
			<br />
		</c:if>
		
		</form:form>
		
		<c:if test="${empty usersTournament}">
			<p class="errorLow_">
				<fmt:message key="error.noRecords"/>
			</p>
		</c:if>
		
		<br /><br />
	</c:if>
	
	<h2>
		<fmt:message key="tournamentUsers.otherUsers"/>:
	</h2><br />
	<div class="blockRight_">
		<img src="../images/find.gif" alt="" border="0" align="top" />
		<input id="searchId2" type="text" onkeyup="return searchInTable('searchId2', 'users');">
	</div>		
	
	<c:set var="totalSize" value="0" />	
	<form:form name="addUser" action="tournamentUsers.jsp" method="post" 
							commandName="tournaments" modelAttribute="tournaments">
	<table id="users" width=100% border=1 cellpadding=3 cellspacing=0>
		<tr>
			<c:if test="${not empty tournamentId && tournamentId > 0}">
            	<th class="tableHeader_"> 
            		
				</th>
          	</c:if>
		
            <th class="tableHeader_"> <fmt:message key="users.nick"/> </th>
            <th class="tableHeader_"> <fmt:message key="users.firstName"/> </th>
            <th class="tableHeader_"> <fmt:message key="users.lastName"/> </th>
            <th class="tableHeader_"> <fmt:message key="registration.address"/> </th>
		</tr>
		
    	<c:forEach var="loop" items="${users}" varStatus="current">
			
			<c:set var="tdclass" value="evenRow_" />
			<c:if test="${current.count % 2 == 0}">
            	<c:set var="tdclass" value="oddRow_" />
          	</c:if>
            
            <tr>
           		<c:if test="${not empty tournamentId && tournamentId > 0}">
           			<td class='<c:out value="${tdclass}" />'>
           				<div class="blockCenter_">
            				<form:checkbox path="id" value="${loop.id}"/>          				
            			</div>
            		</td>
            	</c:if>
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
        	
        	<c:set var="totalSize" value="${totalSize + 1}" />
		</c:forEach>
	</table>
		
		<input type="hidden" name="tournamentId" value="${tournamentId}"/>
		
		<c:if test="${not empty tournamentId && tournamentId > 0 && not empty users}">
			<br />
			<img src="../images/rise.gif" alt="" border="0" align="middle" />
			<button class="send_button2" type="submit" name="action" value="Add" onclick="this.form.submit();">
				<fmt:message key="button.Add"/>
			</button>					
			<br />
		</c:if>
		
		<form:errors path="*" cssClass="errorMessage"/>	
	</form:form>	
	
	<c:if test="${empty users}">
		<p class="errorLow_">
			<fmt:message key="error.noRecords"/>
		</p>
	</c:if>
			
	<br />	
	</div>	
<jsp:include page="elements/footer.jsp"/>