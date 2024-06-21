<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- User.jsp -->

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Main.css -->
	<link rel="stylesheet" type="text/css" href="css/User.css" />
	
	<title>
		<bean:message key="student"/>
	</title>
</head>
<body>
	<table width=100% border=0>
		<tr>
			<td class="logo_">  </td>
			<td> 
				<html:link styleClass="address_" action="main.do">
					<bean:message key="static.mainpage"/>
				</html:link>	
				<bean:message key="bigger"/> 
				<span class="address_"> <bean:message key="static.mainpage.users"/> </span>
			</td>
		</tr>	
	</table>
	<br />
	
	<logic:empty name="peopleForm" property="people">
		<bean:message key="error.noValue"/>
	</logic:empty>
	<logic:notEmpty name="peopleForm" property="people">
		
		<table width=100% border=0>		
			<tr>
				<td>
					<div class="title_">
						<bean:message key="users"/><bean:message key="colon"/>
					</div>
				</td>
				<td width="60%" align="center">		
					<bean:size id="size" name="peopleForm" property="people"/>	
					<c:set var="totalSize" scope="request" value="${size}" />		
					<c:set var="chunkSize" scope="request" value="${peopleForm.chunkSize}" />
					
					<c:forEach var="selection" begin="1" end="${totalSize}" step="${chunkSize}">
						
							<jsp:useBean id="params" class="java.util.HashMap" />
							<c:set target="${params}" property="start" value="${selection - 1}" />
							<c:set target="${params}" property="chunkSize" value="${chunkSize}" />
							
							<c:set var="tableScrollClass" value="tableScroll_" />
							<c:if test="${peopleForm.start == (selection-1)}">
            					<c:set var="tableScrollClass" value="tableScrollSelected_" />
            				</c:if>
            				
            				<span class="<c:out value='${tableScrollClass}' />">
							<html:link action="people.do" name="params" >								
								<c:if test="${(selection -1 + chunkSize) >= totalSize}">
									<c:if test="${totalSize == selection}">
										(<c:out value="${totalSize}" />)
									</c:if>
									<c:if test="${totalSize != selection}">
										(<c:out value="${selection}" /> - <c:out value="${totalSize}" />)
									</c:if>
								</c:if>
								<c:if test="${(selection -1 + chunkSize) < totalSize}">
									<c:if test="${selection != (selection - 1 + chunkSize)}">
										(<c:out value="${selection}" /> - <c:out value="${selection - 1 + chunkSize}" />)
									</c:if>
									<c:if test="${selection == (selection - 1 + chunkSize)}">
										(<c:out value="${selection}" />)
									</c:if>
								</c:if>
							</html:link>
							</span>
					</c:forEach>
					
				</td>
				<td>
					<div class="tableShowString_">
						<html:form action="people.do" method="post">
							<bean:message key="showOn"/>
							<html:text styleClass="chunkSize_" name="peopleForm" property="chunkSize" size="4"/>
							<bean:message key="strings"/>
						</html:form>
					</div>
				</td>
			</tr>
		</table>
		<br />
		
		
		<table width=100% border=1 cellpadding=3 cellspacing=0>
			
			<tr>
           		<th class="tableHeader_"> <bean:message key="code"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.surname"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.name"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.patronymic"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.login"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.password"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.peopleType"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.age"/> </th>
            	<th class="tableHeader_"> <bean:message key="bean.people.address"/> </th>
           	</tr>
			
			<c:set var="start" scope="request" value="${peopleForm.start}" />
			<c:set var="finish" scope="request" value="${start + chunkSize - 1}" />
			
			<c:forEach var="loopPeople" items="${peopleForm.people}" 
						varStatus="current" begin="${start}" end="${finish}">
				
				<c:set var="tdclass" value="evenRow_" />
				<c:if test="${current.count % 2 == 0}">
            		<c:set var="tdclass" value="oddRow_" />
          		</c:if>
            	
            	<tr>
           			<td class='<c:out value="${tdclass}" />'>
						<c:out value="${loopPeople.id_People}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.surname}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.name}" escapeXml="false"/>
            		</td>
            			<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.patronymic}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.login}" escapeXml="false"/>
            		</td>            		
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.password}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.people_type}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.age}" escapeXml="false"/>
            		</td>
            		<td class='<c:out value="${tdclass}" />'>
            			<c:out value="${loopPeople.address}" escapeXml="false"/>
            		</td>
           		</tr>
            </c:forEach>            				
		</table>	
		<br />		
	</logic:notEmpty>
	
	
	<div class="modifyAddress_">
		<html:img src="css/img/manipulate.jpg" alt="" border="0" align="absmiddle" />
		<html:link href="ModifyUser.jsp">	
			<bean:message key="forward.manipulationWith.users"/>
		</html:link>
	</div>
</body>
</html:html>