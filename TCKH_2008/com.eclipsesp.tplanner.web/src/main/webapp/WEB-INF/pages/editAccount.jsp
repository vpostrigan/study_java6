<%-- 
/******************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * EditAccount page
 * 
 * $Revision: 920 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-14 20:05:11 +0300 (Чт, 14 авг 2008) $
 * ****************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

	<!-- editAccount.jsp -->
	<jsp:include page="elements/header.jsp"/>
	
	<div id="leftmenu"> 
   		<tags:mainMenu/>
    </div>

<div id="content">
<div class="modifyForm_">
	<fieldset>
		<legend>
			<fmt:message key="accountEdit.title" />
		</legend>
			
		<form:form action="editAccount.html" method="post" 
						commandName="accountEdit" modelAttribute="accountEditForm">
			
			<form:errors path="*" cssClass="mainError"/>
			<br/>
			 
			<label><fmt:message key="registration.eMail" />*:</label>
				<form:input path="email"/>
			<br/>
			
			<label><fmt:message key="registration.password" />*:</label>
				<form:password path="password"/>
			<br/>
			
			<label><fmt:message key="registration.passwordConfirm" />*:</label>
				<form:password path="passwordconf"/>
			<br/>
			
			<label><fmt:message key="registration.nick" />*:</label>
				<form:input path="nick"/>
			<br/>
			
			<label><fmt:message key="registration.name" />*:</label>
				<form:input path="firstName"/>
			<br/>
			
			<label><fmt:message key="registration.surname" />:</label>
				<form:input path="lastname"/>
			<br/>
						
			<label><fmt:message key="registration.city" />:</label>
				<form:input path="city"/>
			<br/>
			
			<label><fmt:message key="registration.address" />:</label>
				<form:input path="address"/>
			<br/>
		
			<label><fmt:message key="registration.zip" />:</label>
				<form:input path="zip"/>
				<br/>
			<button value="send" class="send_button" style="float: left;" onclick="this.form.submit();">Ok</button>
			<button type="button" class="send_button" style="float: left;" value="back" onClick="JavaScript:window.history.back()" >Back</button>
		</form:form>
			<div class="modifyForm_" style="float:right;">
				<a href="tournaments.jsp?ownerId=${accountEditForm.id}">
					<fmt:message key="tournamentEdit.title" />
				</a>
			</div>
	</fieldset>	
</div>
</div>
<jsp:include page="elements/footer.jsp"/>