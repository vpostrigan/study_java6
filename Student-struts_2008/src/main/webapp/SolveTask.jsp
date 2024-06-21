<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- SolveTask.jsp -->

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
				<span class="address_"> <bean:message key="static.mainpage.solveTask"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<html:form action="/solveTask.do" method="post">
		<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.task"/><bean:message key="colon"/> </label>
				<logic:empty name="solveTaskForm" property="taskAll">
					<bean:message key="error.noValue"/>
				</logic:empty>
				<logic:notEmpty name="solveTaskForm" property="taskAll">
					<html:select styleClass="select-button" name="solveTaskForm" property="selectedTask">
						<html:optionsCollection name="solveTaskForm" property="taskAll" value="id_Task" label="name"/>
					</html:select>
				</logic:notEmpty>
				
				<html:submit styleClass="select-button" property="buttonShow">
					<bean:message key="button.show"/>
				</html:submit>
			</td>			
		</tr>
		</table>
		<hr />
		
		<logic:empty name="solveTaskForm" property="task">
			<bean:message key="error.noValue"/>
		</logic:empty>
		<logic:notEmpty name="solveTaskForm" property="task">
			<label> <bean:message key="bean.task.name"/><bean:message key="colon"/> </label>
				<span class="textValue_"> <bean:write name="solveTaskForm" property="task.name" /> </span> <br /><br /> 
			
			<label> <bean:message key="bean.task.date"/><bean:message key="colon"/> </label>
				<span class="textValue_"> <bean:write name="solveTaskForm" property="task.date" /> </span> <br /><br /> 
					
			<table width=100% border=0>
				<tr>
					<td width=60%>
						<label> <bean:message key="bean.alternative.name"/><bean:message key="colon"/> </label>				
						<table border=0>					
							<logic:empty name="solveTaskForm" property="alternative">
								<bean:message key="error.noValue"/>
							</logic:empty>
							<logic:notEmpty name="solveTaskForm" property="alternative">
								<logic:iterate id="loop" name="solveTaskForm" property="alternative">
									<tr>
										<td class="textValue_">						
											<span class="textValue_">
												<bean:write name="loop" property="alternativeName"/>
											</span>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>			
						</table>
					</td>
				</tr>
			</table>
		
			<table width=100% border=0>
				<tr>
					<td width=60%>
						<label> <bean:message key="bean.criterion.name"/><bean:message key="colon"/> </label>
				
						<table border=0>					
							<logic:empty name="solveTaskForm" property="criterion">
								<bean:message key="error.noValue"/>
							</logic:empty>
							<logic:notEmpty name="solveTaskForm" property="criterion">
								<logic:iterate id="loop" name="solveTaskForm" property="criterion">
									<tr>
										<td class="textValue_">
											<span class="textValue_">
												<bean:write name="loop" property="criterionName"/>
											</span>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
			</table>
			<hr />
		</logic:notEmpty>
		
		<logic:notEmpty name="solveTaskForm" property="result">
			<label class="labelResult_"> <bean:message key="result"/><bean:message key="colon"/> </label>
				<table border="1" cellpadding="3" cellspacing="0">
						<tr>
							<th class="tableHeader_"> <bean:message key="bean.alternative.name" /> </th>
							<th class="tableHeader_"> <bean:message key="result"/> </th>
						</tr>
					<logic:iterate id="loop" name="solveTaskForm" property="result">
						<tr>
							<td align="center"> <bean:write name="loop" property="id_People"/> </td>				
							<td align="center"> <bean:write name="loop" property="email"/> </td>
						</tr>
					</logic:iterate>
				</table> <br />
		</logic:notEmpty>
		
		<logic:empty name="solveTaskForm" property="alternative">
			<logic:empty name="solveTaskForm" property="criterion">
				<bean:message key="error.noValueForSolve"/>"<bean:message key="static.mainpage.taskConditions"/>"
			</logic:empty>
		</logic:empty>
		<logic:notEmpty name="solveTaskForm" property="alternative">
			<logic:notEmpty name="solveTaskForm" property="criterion">
				
				<logic:iterate id="loop" name="solveTaskForm" property="criterion" indexId="count">
					<label> <bean:message key="bean.criterion.name"/>
							&nbsp;<c:out value="${count+1}"/>&nbsp;<bean:message key="colon"/> </label>
					<table border=0 cellpadding="1" cellspacing="1">
						<logic:iterate id="loop" name="solveTaskForm" property="alternative" indexId="countRow">
							<tr>
								<logic:iterate id="loop" name="solveTaskForm" property="alternative" indexId="countCol">
           							<td>
           								<html:text size="2" maxlength="1" name="solveTaskForm" property="value" />           								
            						</td>
       							</logic:iterate>
       						</tr>
       					</logic:iterate>
       				</table> <br />
				</logic:iterate>
								
				<html:submit styleClass="submit-button" property="buttonSolve">
					<bean:message key="button.solve"/>
				</html:submit>
				<span class="errorLow_"> <html:errors property="error.solve"/> </span>
				
			</logic:notEmpty>
		</logic:notEmpty>
		
	</html:form>
</body>
</html:html>