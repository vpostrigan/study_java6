<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- ResumeLook.jsp -->

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
		sfFocus = function() {
			var sfEls = document.getElementsByTagName("INPUT");
			for (var i=0; i<sfEls.length; i++) {
				sfEls[i].onfocus=function() {
					this.className+=" sffocus";
				}
				sfEls[i].onblur=function() {
					this.className=this.className.replace(new RegExp(" sffocus\\b"), "");
				}
			}
		}
		if (window.attachEvent) window.attachEvent("onload", sfFocus);		
		
		sfFocus2 = function() {
			var sfEls2 = document.getElementsByTagName("TEXTAREA");
			for (var i=0; i<sfEls2.length; i++) {
				sfEls2[i].onfocus=function() {
					this.className+=" sffocus2";
				}
				sfEls2[i].onblur=function() {
					this.className=this.className.replace(new RegExp(" sffocus2\\b"), "");
				}
			}
		}
		if (window.attachEvent) window.attachEvent("onload", sfFocus2);
		
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
				<span class="address_"> <bean:message key="static.mainpage.view"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<html:form action="/resumeLook.do" method="post">
	
		<label> <bean:message key="student"/><bean:message key="colon"/> </label>
			<logic:empty name="resumeLookForm" property="people_Receiver">
				<bean:message key="error.noValue"/>
			</logic:empty>
			<logic:notEmpty name="resumeLookForm" property="people_Receiver">
				<html:select styleClass="select-button" name="resumeLookForm" property="student.id_People">
					<html:optionsCollection name="resumeLookForm" property="people_Receiver" value="id_People" label="email"/>
				</html:select>
			</logic:notEmpty>
			
		<html:submit styleClass="select-button" property="buttonShow">
			<bean:message key="button.show"/>
		</html:submit>
		
		<logic:empty name="resumeLookForm" property="student">
			<bean:message key="error.noValue"/>			
		</logic:empty>
		<logic:notEmpty name="resumeLookForm" property="student">			
			<bean:write name="resumeLookForm" property="student.resume" filter="false"/>
		</logic:notEmpty>
	</html:form> <br />	
	
</body>
</html:html>