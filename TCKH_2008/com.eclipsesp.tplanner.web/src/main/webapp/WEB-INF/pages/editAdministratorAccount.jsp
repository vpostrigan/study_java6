<%-- 
/******************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * EditAccount page
 * 
 * $Revision: 1 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-11 15:53:48 +0300 (Пн, 12 авг 2008) $
 * ****************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

						<!-- editAdministratorAccount.jsp -->
	<jsp:include page="elements/header.jsp"/>
	<div id="leftmenu"> 
   		<tags:mainMenu/>
    </div>
    
<div class="modifyForm_">
	<fieldset>
		<legend>
			<fmt:message key="accountEdit.title" />
		</legend>
			
		
			
		<form:form action="editAdministratorAccount.html" method="post" 
						commandName="accountAdministratorEdit" modelAttribute="accountAdministratorEdit">
			
			<form:errors path="*" cssClass="errorMessage"/>
			<br/>
			<form:hidden path="id"/> 
			<label><fmt:message key="registration.eMail" /><c:out value="*:"/></label>
				<form:input path="email"/>	
			<br/>
			
			<label><fmt:message key="registration.password" /><c:out value="*:"/></label>
				<form:password path="password"/>
			<br/>
			
			<label><fmt:message key="registration.passwordConfirm" /><c:out value="*:"/></label>
				<form:password path="passwordconf"/>
			<br/>
			
			<label><fmt:message key="registration.nick" /><c:out value="*:"/></label>
				<form:input path="nick"/>
			<br/>
			
			<label><fmt:message key="registration.name" /><c:out value="*:"/></label>
				<form:input path="firstName"/>
			<br/>
			
			<label><fmt:message key="registration.surname" /><c:out value=":"/></label>
				<form:input path="lastname"/>
			<br/>
						
			<label><fmt:message key="registration.city" /><c:out value=":"/></label>
				<form:input path="city"/>
			<br/>
			
			<label><fmt:message key="registration.address" /><c:out value=":"/></label>
				<form:input path="address"/>
			<br/>
		
			<label><fmt:message key="registration.zip" /><c:out value=":"/></label>
				<form:input path="zip"/>
			<br/>
 			<label><fmt:message key="registration.role_name" /><c:out value="*:"/></label>
				<form:select path="role_Name" >
 					 <c:forEach var="role" items="${roleNameAll}" >	
 						 <c:if test="${role.roleName eq accountAdministratorEdit.role_Name}" var="flag">
							<option selected="selected" value="${role.roleName}"><c:out value="${role.roleName}"/></option>
       					 </c:if>
       					 <c:if test="${flag ne true}">
							<option value="${role.roleName}"><c:out value="${role.roleName}"/></option>
						 </c:if>	       				 
       				</c:forEach>
       			</form:select>
			<br/>
			<label><fmt:message key="registration.description" /><c:out value=":"/></label>
				<form:textarea  path="description"/>
			<br/>
			<button value="edit" name = "action" class="send_button" style="float: left;" onclick="this.form.submit();">Ok</button>
			<button type="button" class="send_button" style="float: left;" value="back" onClick="JavaScript:window.history.back()" >Back</button>
			<button value="delete" name = "action" class="send_button" style="float: left;" onclick="return confirm('Are you sure?');this.form.submit();">Delete</button>
		</form:form>
		<div class="modifyForm_" style="float:right;">
			<a href="tournaments.jsp?ownerId=${accountAdministratorEdit.id}">
				<fmt:message key="tournamentEdit.title" />
			</a>
		</div>
	</fieldset>
</div>
<jsp:include page="elements/footer.jsp"/>