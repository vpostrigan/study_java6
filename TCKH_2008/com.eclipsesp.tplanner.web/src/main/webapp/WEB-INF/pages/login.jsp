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


<div class="form" style="margin-left:325px;margin-right:325px">
	<fieldset>
		<legend>
			<fmt:message key="login.signIn" />
		</legend>

		<form:form action="login.html" method="post" commandName="loginform">
			<form:errors path="*" cssClass="errorMessage"/>
			<br/>
			
			<label for="email"><fmt:message key="login.eMail" />:</label>
				<form:input maxlength="40" path="email"/>
			<br/>
		
			<label for="password"><fmt:message key="login.password" />:</label>
				<form:password maxlength="200" path="password"/>
			<br/>
		
			<br/>
			<div class="blockRight_">
			<button class="send_button2" type="submit" value="send" onclick="this.form.submit();">
				<fmt:message key="login.signIn" />
			</button> 
			</div>
			
			<div class="blockLeft_">
			<a href="registration.html"> 
				<fmt:message key="login.register" />
			</a>
			</div>
		</form:form>
	</fieldset>
</div>
<br />
<jsp:include page="elements/footer.jsp"/>
