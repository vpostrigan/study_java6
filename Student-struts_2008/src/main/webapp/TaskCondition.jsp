<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- TaskCondition.jsp -->

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
	</script>
	
	<table width=100% border=0>
		<tr>
			<td class="logo_">  </td>
			<td>
				<html:link styleClass="address_" action="main.do">
					<bean:message key="static.mainpage"/>
				</html:link>
				<bean:message key="bigger"/>
				<span class="address_"> <bean:message key="static.mainpage.taskConditions"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<html:form action="/taskCondition.do" method="post">
		<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.task"/><bean:message key="colon"/> </label>
				<logic:empty name="taskConditionForm" property="taskAll">
					<bean:message key="error.noValue"/>
				</logic:empty>
				<logic:notEmpty name="taskConditionForm" property="taskAll">
					<html:select styleClass="select-button" name="taskConditionForm" property="selectedTask">
						<html:optionsCollection name="taskConditionForm" property="taskAll" value="id_Task" label="name"/>
					</html:select>
				</logic:notEmpty>
				
				<html:submit styleClass="select-button" property="buttonShow">
					<bean:message key="button.show"/>
				</html:submit>
			</td>
			<td align="right">
				<label> <bean:message key="modify.newTask"/><bean:message key="colon"/> </label>
					<html:text maxlength="30" property="newTask" />
					
					<html:submit property="buttonAdd">
						<bean:message key="button.add"/>
					</html:submit>
					<span class="errorLow_"> <html:errors property="error.insert" /> </span> <br />
			</td>
		</tr>
		</table>
	<br />
	
	<logic:empty name="taskConditionForm" property="task">
		<bean:message key="error.noValue"/>
	</logic:empty>
	<logic:notEmpty name="taskConditionForm" property="task">
		<label> <bean:message key="bean.task.name"/><bean:message key="colon"/> </label>
			<span class="textValue_"> <bean:write name="taskConditionForm" property="task.name" /> </span> <br /><br /> 
			
		<label> <bean:message key="bean.task.date"/><bean:message key="colon"/> </label>
			<span class="textValue_"> <bean:write name="taskConditionForm" property="task.date" /> </span> <br /><br /> 
		<br />
		
		
		<hr />		
		<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.alternative.name"/><bean:message key="colon"/> </label>
				
				<table border=0>					
					<logic:empty name="taskConditionForm" property="alternative">
						<bean:message key="error.noValue"/>
					</logic:empty>
					<logic:notEmpty name="taskConditionForm" property="alternative">
						<logic:iterate id="loop" name="taskConditionForm" property="alternative">
							<tr>
								<td class="textValue_">
									<html:multibox styleClass="checkBox_" name="taskConditionForm" 
																			property="selectedItemsAlternative">
										<bean:write name="loop" property="alternativeName"/>
									</html:multibox>
						
									<span class="textValue_">
										<bean:write name="loop" property="alternativeName"/>
									</span>
								</td>
							</tr>
						</logic:iterate>
							<tr>
								<td class="textValue_">
									<html:submit styleClass="select-button" property="buttonDeleteAlternative">
										<bean:message key="button.delete.selected"/>
									</html:submit>
									<span class="errorLow_"> <html:errors property="error.alternative.delete"/> </span>
								</td>
							</tr>
					</logic:notEmpty>			
				</table>
			</td>
			<td align="right">
				<label> <bean:message key="modify.newAlternative"/><bean:message key="colon"/> </label>	
					<html:text maxlength="30" name="taskConditionForm" property="newAlternative" />
					
					<html:submit property="buttonAddAlternative">
						<bean:message key="button.add"/>
					</html:submit>
					<span class="errorLow_"> <html:errors property="error.alternative.add"/> </span>					
			</td>
		</tr>
		</table>		
		<hr />
		
		<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.criterion.name"/><bean:message key="colon"/> </label>
				
				<table border=0>					
					<logic:empty name="taskConditionForm" property="criterion">
						<bean:message key="error.noValue"/>
					</logic:empty>
					<logic:notEmpty name="taskConditionForm" property="criterion">
						<logic:iterate id="loop" name="taskConditionForm" property="criterion">
							<tr>
								<td class="textValue_">
									<html:multibox styleClass="checkBox_" 
														name="taskConditionForm" property="selectedItemsCriterion">
										<bean:write name="loop" property="criterionName"/>
									</html:multibox>
						
									<span class="textValue_">
										<bean:write name="loop" property="criterionName"/>
									</span>
								</td>
							</tr>
						</logic:iterate>
							<tr>
								<td class="textValue_">
									<html:submit styleClass="select-button" property="buttonDeleteCriterion">
										<bean:message key="button.delete.selected"/>
									</html:submit>
									<span class="errorLow_"> <html:errors property="error.criterion.delete"/> </span>
								</td>
							</tr>
					</logic:notEmpty>			
				</table>
			</td>
			<td align="right">
				<label> <bean:message key="modify.newCriterion"/><bean:message key="colon"/> </label>	
					<html:text maxlength="30" name="taskConditionForm" property="newCriterion" />
					
					<html:submit property="buttonAddCriterion">
						<bean:message key="button.add"/>
					</html:submit>
					<span class="errorLow_"> <html:errors property="error.criterion.add" /> </span>					
			</td>
		</tr>
		</table> <hr />	
	</logic:notEmpty>
	</html:form>
	
</body>
</html:html>