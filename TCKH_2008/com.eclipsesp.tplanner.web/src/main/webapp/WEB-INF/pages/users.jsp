<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

	<!-- users.jsp -->

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
				<form:form name="search" action="users.jsp" method="post" 
										commandName="searchUsers" modelAttribute="searchUsersForm">					
					<input type="image" id="imageId" src="../images/find.gif" alt="" border="0" align="absmiddle" />
					<form:input path="search"/>
				</form:form>
			</td>
		</tr>
	</table>
	<br />
	
	<div>
		<table class="tableCenter_" border=0>
			<tr>			
					<td align="center">   
						<c:if test="${param.l != '#'}">
							<span class="menu2_"> 						    				
           						<a class="menu2_" href="?l=%23">
           							#
           						</a>
           					</span>
           				</c:if> 
           				<c:if test="${param.l == '#'}">
           					<span class="menuChose_">
           						#
           					</span>
           				</c:if> 
            		</td>
				<c:forEach var="loop" items="${letters}">
           			<td align="center">  
           				<c:if test="${param.l != loop.letter}">
							<span class="menu2_">           				
           						<a class="menu2_" href="?l=<c:out value='${loop.letter}'/>">
           							<c:out value="${loop.letter}" escapeXml="false"/>
           						</a>	
           					</span>	
           				</c:if> 
           				<c:if test="${param.l == loop.letter}">
           					<span class="menuChose_">
           						<c:out value="${loop.letter}" escapeXml="false"/>
           					</span>
           				</c:if> 	
            		</td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<br />
	
	<c:set var="totalSize" value="0" />
	
	<form:form name="addUser" action="users.jsp" method="post" 
							commandName="tournaments" modelAttribute="tournaments">
	<table width=100% border=1 cellpadding=3 cellspacing=0>
		<tr>
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
           		<td class='<c:out value="${tdclass}" />'>
           			<a href="account.jsp?id=<c:out value='${loop.id}'/>">
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
		
		<form:errors path="*" cssClass="errorMessage"/>	
	</form:form>	
	
	<c:if test="${empty users}">
		<p class="errorLow_">
			<fmt:message key="error.noRecords"/>
		</p>
	</c:if>
	
	<p></p>
		
	<tplannerPager:pager size="${totalSize}" langPages="${page2}" 
		langNext="pager.next" langPrevious="pager.previous"/>	
		
	</div>	
<jsp:include page="elements/footer.jsp"/>