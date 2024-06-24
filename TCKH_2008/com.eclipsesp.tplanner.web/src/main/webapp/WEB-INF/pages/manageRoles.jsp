<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="elements/header.jsp"/>


<div class="form" style="margin-left: 200px; margin-right: 200px;">
	<fieldset>
		<legend>
			<fmt:message key="manage.title" />
		</legend>

		<form:form action="manageroles.html" method="post" commandName="roleForm">
			<form:errors path="*" cssClass="mainError"/>
		
			<label for="roleName"><fmt:message key="manage.roleName" />:</label>
				<form:input maxlength="20" path="roleName"/>
			<br/>
		
			<label for="userAccess"><fmt:message key="manage.accessToUsers" />:</label>
				<form:select path="userAccess" multiple="false">
					<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_NOTHING.getId()%>"><fmt:message key="manage.canNothing" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_SELF.getId()%>"><fmt:message key="manage.canReadSelf" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_ALL.getId()%>"><fmt:message key="manage.canReadAll" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_WRITE_SELF.getId()%>"><fmt:message key="manage.canWriteSelf" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_WRITE_ALL.getId()%>"><fmt:message key="manage.canWriteAll" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_ALL_WRITE_SELF.getId()%>"><fmt:message key="manage.canReadAllWriteSelf" /></option>
				</form:select>
			<br/>
			
			<label for="tournamentAccess"><fmt:message key="manage.accessToTournaments" />:</label>
				<form:select path="tournamentAccess" multiple="false">
					<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_NOTHING.getId()%>"><fmt:message key="manage.canNothing" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_SELF.getId()%>"><fmt:message key="manage.canReadSelf" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_ALL.getId()%>"><fmt:message key="manage.canReadAll" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_WRITE_SELF.getId()%>"><fmt:message key="manage.canWriteSelf" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_WRITE_ALL.getId()%>"><fmt:message key="manage.canWriteAll" /></option>
        			<option value="<%=com.eclipsesp.tplanner.core.security.utils.SinglePermission.CAN_READ_ALL_WRITE_SELF.getId()%>"><fmt:message key="manage.canReadAllWriteSelf" /></option>
				</form:select>
			<br/>
		
			<button class="send_button" type="submit" value="send" onclick="this.form.submit();">SEND</button> 
			<br />
		</form:form>
</fieldset>
</div>
<jsp:include page="elements/footer.jsp"/>