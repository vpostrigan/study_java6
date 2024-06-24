<%--
/* *****************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * Home page
 * 
 * $Revision: 170 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-24 21:12:18 +0300 (чт, 24 лип 2008) $
 * ****************************************************************************/
--%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="elements/header.jsp"/>
<div class="form">
	<fieldset>
		<legend>
			<fmt:message key="registration.title" />
		</legend>
			
		<form:form action="registration.html" method="post" commandName="regform" modelAttribute="regform">
			<form:errors path="*" cssClass="errorMessage"/>
			<br/>
			<label><fmt:message key="registration.eMail" />*:</label>
				<form:input maxlength="40" path="email"/>
			<br/>
			
			<label><fmt:message key="registration.password" />*:</label>
				<form:password maxlength="200" path="password"/>
			<br/>
			
			<label><fmt:message key="registration.passwordConfirm" />*:</label>
				<form:password maxlength="200" path="passwordconf"/>
			<br/>
			
			<label><fmt:message key="registration.nick" />*:</label>
				<form:input maxlength="12" path="nick"/>
			<br/>
			
			<label><fmt:message key="registration.name" />*:</label>
				<form:input maxlength="20" path="firstName"/>
			<br/>
			
			<label><fmt:message key="registration.surname" />:</label>
				<form:input maxlength="20" path="lastname"/>
			<br/>
						
			<label><fmt:message key="registration.city" />:</label>
				<form:input maxlength="20" path="city"/>
			<br/>
			
			<label><fmt:message key="registration.address" />:</label>
				<form:input maxlength="100" path="address"/>
			<br/>
		
			<label><fmt:message key="registration.zip" />:</label>
				<form:input maxlength="6" path="zip"/>
				<br/>
			<label><fmt:message key="registration.captcha" />*:</label>
				<input maxlength="5" id="captcha" name="captcha"/>
		<br/>
		<img src="Captcha.jpg"/>
		<br/>
		<br/>
		<label><fmt:message key="registration.starmean" /></label>
		<br/>
		<button class="send_button" style="	float: left;" type="submit" value="send" onclick="this.form.submit();">SEND</button> 
		<br clear="all" />
		</form:form>
	</fieldset>
	
</div>
<jsp:include page="elements/footer.jsp"/>
