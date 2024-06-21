<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="elements/header.jsp" flush="true">
	<jsp:param name="selectedPage" value="/pages/login.jsp"/>
</jsp:include>

<div class="form1" style="margin-left:325px; margin-right:325px">
	<fieldset>
		<legend><fmt:message key="login.signIn" /></legend>

		<form:form name="login" action="login.jsp" method="post" 
				commandName="loginform" modelAttribute="loginform">
			<!-- <form:errors path="*" cssClass="errorMessage" /> -->
			<br/>
			
			<label for="email"><fmt:message key="login.eMail" />:</label>
				<form:input maxlength="40" path="email"/>
			<br/>
		
			<label for="password"><fmt:message key="login.password" />:</label>
				<form:password maxlength="200" path="password"/>
			<br/>
					
			<button class="button" type="submit" name="send">
				<fmt:message key="login.signIn" />
			</button>
			<br/>
			
			<a href="registration.html"> 
				<fmt:message key="login.register" />
			</a>
		</form:form>
	</fieldset>
</div>

<jsp:include page="elements/footer.jsp"/>