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


<div class="form" style="margin-left: 250px; margin-right: 250px;">
	<fieldset>
		<legend>
			<fmt:message key="promo.title" />
		</legend>

		<form:form action="promosend.html" method="post" commandName="promoform">
			<form:errors path="*" cssClass="errorMessage"/>
			<br/>
			
			<label for="ownerEmail"><fmt:message key="promo.ownerEmail" />:</label>
				<form:input readonly="true" path="ownerEmail"/>
			<br/>
		
			<label for="email"><fmt:message key="promo.email" />:</label>
				<form:input path="email"/>
			<br/>
			<form:hidden path="id"/>
			<button type="button" class="send_button" style="float: right;" value="back" onclick="JavaScript:window.history.back()" >BACK</button>
			<button class="send_button" type="submit" value="send" onclick="this.form.submit();">SEND</button> 
			<br/>
		</form:form>
	</fieldset>
</div>
<jsp:include page="elements/footer.jsp"/>
