<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- Profile.jsp -->

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Profile.css -->
	<link rel="stylesheet" type="text/css" href="css/Profile.css" />
	
	<title>
		<bean:message key="student"/>
	</title>
</head>
<body>

	<script type="text/javascript">
		function imposeMaxLength(Object, MaxLen){
  			return (Object.value.length <= MaxLen);
		}
	</script>

	<table width=100% border=0>
		<tr>
			<td class="logo_">  </td>
			<td>
				<html:link styleClass="address_" action="main.do">
					<bean:message key="static.mainpage"/>
				</html:link>
				<bean:message key="bigger"/>
				<span class="address_"> <bean:message key="static.mainpage.resume"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<html:form action="/resume.do" method="post">		
		<label><bean:message key="bean.student.resume"/><bean:message key="colon"/></label>
			<html:textarea styleClass="textValue_" rows="30" cols="110" onkeypress="return imposeMaxLength(this, 4999);"
				name="resumeForm" property="student.resume"/> 
		<br />
		
		<html:submit styleClass="submit-button" property="buttonAdd">
			<bean:message key="button.edit"/>
		</html:submit>
		<span class="errorLow_"> <html:errors property="error.edit" /> </span>
	</html:form>

</body>
</html:html>