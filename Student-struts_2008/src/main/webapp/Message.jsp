<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- Message.jsp -->

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
				<span class="address_"> <bean:message key="static.mainpage.message"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<p align=right>
		<span class="menu_">
			<a href='message.do?newMessage=true'>
				<html:img src="css/img/messageAdd.jpg" alt="" border="0" align="absmiddle" />
				<bean:message key="write.newMessage"/>
			</a>
		</span>
	</p>
	
	<!-- Reading the message -->
	<logic:notEmpty name="messageForm" property="id_Message">
		<logic:notEqual name="messageForm" property="id_Message" value="0">
			<label><bean:message key="bean.message.date"/><bean:message key="colon"/></label>
				<span class="textValue_"><bean:write name="messageForm" property="messageRead.message_Date"/></span> <br />
			<br />
			<label><bean:message key="bean.message.theme"/><bean:message key="colon"/></label>
				<span class="textValue_"><bean:write name="messageForm" property="messageRead.theme"/></span> <br />		
			<br />
			<label><bean:message key="bean.message.message"/><bean:message key="colon"/></label>
				<bean:write name="messageForm" property="messageRead.message" filter="false"/> <br />
			<br />		
			<logic:notEmpty name="messageForm" property="menuList">
				<label><bean:message key="bean.message.attachments"/><bean:message key="colon"/></label>
				
				<logic:iterate id="loop" name="messageForm" property="menuList">					
					<span class="menu_">
            			<a href='<c:out value="${loop.menuItem}"/>'>
							<bean:write name="loop" property="url"/>
						</a>
            		</span> <br />
				</logic:iterate>				
			</logic:notEmpty>	
			<br />
		</logic:notEqual>		
	</logic:notEmpty>
	
	<!-- New message -->
	<logic:notEmpty name="messageForm" property="newMessage">
		<logic:notEqual name="messageForm" property="newMessage" value="false">
			<html:form action="/message.do" method="post">
				<p class="title_">
					<bean:message key="newMessage"/><bean:message key="colon"/>
				</p>
				
				<label for="peopleType"><bean:message key="write.messageForUser"/><bean:message key="colon"/></label>			
					<logic:empty name="messageForm" property="people_Receiver">
						<bean:message key="error.noValue"/>
					</logic:empty>
					<logic:notEmpty name="messageForm" property="people_Receiver">
						<html:select property="messageNew.id_People_Receiver">
							<html:optionsCollection name="messageForm" property="people_Receiver" value="id_People" label="email"/>
						</html:select>
					</logic:notEmpty>
				<br /><br />
								
				<label><bean:message key="bean.message.theme"/><bean:message key="colon"/></label>
					<html:text size="50" maxlength="30" property="messageNew.theme" />		
					<span class="address_"> <html:errors property="error.messageNew.theme.add" /> </span>  <br />
					
				<label><bean:message key="bean.message.message"/><bean:message key="colon"/></label>
					<html:textarea rows="8" cols="100" onkeypress="return imposeMaxLength(this, 4999);" 
								property="messageNew.message"/> <br />	
				
				<logic:notEmpty name="messageForm" property="menuListNew">				
					<label><bean:message key="bean.message.attachments"/><bean:message key="colon"/></label>
					
					<logic:iterate id="loop" name="messageForm" property="menuListNew">			
						<span class="menu_">
            				<a href='<c:out value="${loop.menuItem}"/>'>
								<bean:write name="loop" property="url"/>
							</a>
            			</span> <br />
					</logic:iterate>
				</logic:notEmpty>
				<br />
				
				<html:submit styleClass="submit-button" property="buttonAdd">
					<bean:message key="button.send"/>
				</html:submit>
				<span class="address_"> <html:errors property="error.insert" /> </span>
			</html:form>
			<html:form action="/message.do" method="post" enctype="multipart/form-data">
				<label><bean:message key="bean.message.attachments"/>(<bean:message key="fileSize"/>)<bean:message key="colon"/></label>
				<html:file property="file" />
				
				<html:submit property="buttonUpload">
					<bean:message key="button.upload"/>
				</html:submit>
				<span class="address_"> <html:errors property="error.file" /> </span> <br />
			</html:form>
		</logic:notEqual>
	</logic:notEmpty>
	<hr />
	
	<!-- Message list -->
	<logic:empty name="messageForm" property="message">
		<bean:message key="error.noValue"/>
	</logic:empty>
	<logic:notEmpty name="messageForm" property="message">
	
		<table width=100% border=0>
			<tr>
				<td>
					<div class="title_">
						<bean:message key="bean.message.input"/><bean:message key="colon"/>
					</div>
				</td>
				<td width="60%" align="center">
					<bean:size id="size" name="messageForm" property="message"/>
					<c:set var="totalSize" scope="request" value="${size}" />
					<c:set var="chunkSize" scope="request" value="${messageForm.chunkSize}" />
					
					<c:forEach var="selection" begin="1" end="${totalSize}" step="${chunkSize}">
						
						<jsp:useBean id="params" class="java.util.HashMap" />
						<c:set target="${params}" property="start" value="${selection - 1}" />
						<c:set target="${params}" property="chunkSize" value="${chunkSize}" />
							
						<c:set var="tableScrollClass" value="tableScroll_" />
						<c:if test="${messageForm.start == (selection-1)}">
            				<c:set var="tableScrollClass" value="tableScrollSelected_" />
            			</c:if>
            			
            			<span class="<c:out value='${tableScrollClass}' />">
						<html:link action="message.do" name="params">
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
						<html:form action="/message.do" method="post">
							<bean:message key="showOn"/>
							<html:text styleClass="chunkSize_" name="messageForm" property="chunkSize" size="4"/>
							<bean:message key="strings"/>
						</html:form>
					</div>
				</td>
			</tr>
		</table> <br />
		
		<html:form action="/message.do" method="post">
			<table width=100% border=1 cellpadding=3 cellspacing=0>
				<tr>
					<th class="tableHeader_"> # </th>
					<th class="tableHeader_"> <bean:message key="bean.message.date"/> </th>
					<th class="tableHeader_"> <bean:message key="bean.message.theme"/> </th>
					<th class="tableHeader_"> <bean:message key="bean.people.login"/> </th>
					<th class="tableHeader_"> <bean:message key="bean.people.name"/> </th>
				</tr>
				
				<c:set var="start" scope="request" value="${messageForm.start}" />
				<c:set var="finish" scope="request" value="${start + chunkSize - 1}" />
				
				<c:forEach var="loop" items="${messageForm.message}" varStatus="current" begin="${start}" end="${finish}">
					
					<c:set var="tdclass" value="oddRow_" />
					<c:if test="${current.count % 2 == 0}">
            			<c:set var="tdclass" value="evenRow_" />
          			</c:if>
            		
            		<tr>
           				<td width=15 align="center" class='<c:out value="${tdclass}" />'>
							<html:multibox styleClass="checkBox_" name="messageForm" property="selectedItems">
								<bean:write name="loop" property="id_Message"/>
							</html:multibox>
            			</td>
            			<td width="90" align="center" class='<c:out value="${tdclass}" />'>
            				<bean:write name="loop" property="message_Date"/>
            			</td>
            			<td align=center class='<c:out value="${tdclass}" />'>
            				<span class="menu_">
            					<a href='message.do?id_Message=<c:out value="${loop.id_Message}"/>'>
									<bean:write name="loop" property="theme"/>
								</a>
            				</span>
            			</td>
            			<td width="15%" align="center" class='<c:out value="${tdclass}" />'>
            				<bean:write name="loop" property="login"/>
            			</td>
            			<td width="20%" align="center" class='<c:out value="${tdclass}" />'>
            				<bean:write name="loop" property="name"/>
            			</td>
           			</tr>
           	 	</c:forEach>
			</table><br />
			
			<html:submit styleClass="select-button" property="buttonDelete">
				<bean:message key="button.delete.selected"/>
			</html:submit>
			<span class="address_"> <html:errors property="error.delete" /> </span> <br />
		</html:form>
	</logic:notEmpty>
</body>
</html:html>