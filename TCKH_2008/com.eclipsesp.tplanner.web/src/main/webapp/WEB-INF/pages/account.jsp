<%-- 
/******************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * Account page
 * 
 * $Revision: 878 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 19:57:43 +0300 (Ср, 13 авг 2008) $
 * ****************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tld/tplanner.tld" prefix="tpl" %>
		<!-- account.jsp -->
		
<jsp:include page="elements/header.jsp"/>
	<div class="viewForm">
	<fieldset>
		<legend>
			<fmt:message key="account.accountDataInformation" />
		</legend>
			
		<form:form action="account.html" method="post" 
						commandName="accountEdit" modelAttribute="accountForm">
			
			<form:errors path="*" cssClass="mainError"/>
			<br/>
			<c:if test="${account.permission eq 255}">
				<fmt:message key="registration.eMail" /><c:out value=":"/>
					<form:label path="email">${accountForm.email}</form:label>
				<br/>
			</c:if>
			<fmt:message key="registration.nick" /><c:out value=":"/>
				<form:label path="nick">${accountForm.nick}</form:label>
			<br/>
			
			<fmt:message key="registration.name" /><c:out value=":"/>
				<form:label path="firstName">${accountForm.firstName}</form:label>
			<br/>
			
			<fmt:message key="registration.surname" /><c:out value=":"/>
				<form:label path="lastname">${accountForm.lastname}</form:label>
			<br/>
						
			<fmt:message key="registration.city" /><c:out value=":"/>
				<form:label path="city">${accountForm.city}</form:label>
			<br/>
			
			<fmt:message key="registration.address" /><c:out value=":"/>
				<form:label path="address">${accountForm.address}</form:label>
			<br/>
		
			<fmt:message key="registration.zip" /><c:out value=":"/>
				<form:label path="zip">${accountForm.zip}</form:label>
			<br/>
			<fmt:message key="registration.role_name" /><c:out value=":"/>
				<form:label path="role_Name">${accountForm.role_Name}</form:label>
			<br/>
			<c:if test="${account.permission eq 255}">
				<fmt:message key="registration.description" /><c:out value=":"/>
					<form:textarea readonly="true" path="description"/>
				<br/>
			</c:if>
		</form:form>
	</fieldset>
	
</div>

		<div id="viewForm_">
		<fieldset>
				<legend>
				<fmt:message key="tournament.title"/> : <fmt:message key="account.accountOwner" />
				</legend>
		
				<form:form action="account.html" method="post" 
								commandName="tournaments" modelAttribute="tournamentView">
					
					<%@include file="elements/tournament/tournamentsView.jsp" %>
					<tpl:table tournamentView="${tournaments}"/>
					<c:if test="${empty tournaments}">
						<p class="errorLow_">
							<fmt:message key="error.noRecords"/>
						</p>
					</c:if>	
					
					<form:errors path="*" cssClass="mainError"/>
					<br/>
		
					</form:form>
		</fieldset>			
		</div>
		<div id="viewForm_">
		<fieldset>
				<legend>
				<fmt:message key="tournament.title"/> : <fmt:message key="account.accountTakePart" />
				</legend>
		
				<form:form action="account.html" method="post" 
								commandName="tournamentsTakePart" modelAttribute="tournamentView">
					
					<%@include file="elements/tournament/tournamentsView.jsp" %>
					<tpl:table tournamentView="${tournamentsTakePart}"/>
					<c:if test="${empty tournamentsTakePart}">
						<p class="errorLow_">
							<fmt:message key="error.noRecords"/>
						</p>
					</c:if>	
					<form:errors path="*" cssClass="mainError"/>
					<br/>
		
					</form:form>
			</fieldset>			
		</div>
		<div id="viewForm_">
		<fieldset>
				<legend>
				<fmt:message key="tournament.title"/> : <fmt:message key="account.pending" />
				</legend>
		
				<form:form action="account.html" method="post" 
								commandName="tournamentsPending" modelAttribute="tournamentView">
					
					<%@include file="elements/tournament/tournamentsView.jsp" %>
					<tpl:table tournamentView="${tournamentsPending}"/>
					<c:if test="${empty tournamentsPending}">
						<p class="errorLow_">
							<fmt:message key="error.noRecords"/>
						</p>
					</c:if>	
					<form:errors path="*" cssClass="mainError"/>
					<br/>
		
					</form:form>
			</fieldset>			
		</div>

<div class="clear"/>

<c:if test="${account.permission eq 255}">
	<c:set var="url" value="editAdministratorAccount.html?id=${param.id}"/>
	<button type="button" class="send_button" style="float: right;" value="Edit" name="Edit" onclick="top.location.href='${url}';" >EDIT</button>
</c:if>

	<button type="button" class="send_button" style="float: right;" value="back" onclick="JavaScript:window.history.back()" >BACK</button>
	
	<div class="clear"/>
	<jsp:include page="elements/footer.jsp"/>
