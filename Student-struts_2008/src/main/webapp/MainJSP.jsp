<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- MainJSP.jsp -->

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Main.css -->
	<link rel="stylesheet" type="text/css" href="css/Main.css" />
	
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
			<td class="address_"> <bean:message key="static.mainpage"/> </td>
						
		</tr>	
	</table>
	<br />
	
	<table width=100% border=0 cellspacing=5>
		<tr>
			<td valign="top" align="left">
				<table border=0>
				<tr>
				<td>
				<fieldset>
					<legend>
						<bean:message key="siteMenu"/>
					</legend>
					
					<ul class="menu_">
						<logic:equal name="mainForm" property="peopleIsAdmin" value="true">
							<li>
								<html:link action="/people.do">
									<bean:message key="static.mainpage.users" /> <br /> <br />
								</html:link>
							</li>
						</logic:equal>
						<logic:equal name="mainForm" property="peopleIsStudent" value="true">
							<li>
								<html:link action="/resume.do">
									<bean:message key="static.mainpage.resume" /> <br /> <br />
								</html:link>
							</li>
						</logic:equal>
						<logic:equal name="mainForm" property="peopleIsTeacher" value="true">
							<li>
								<html:link action="/resumeLook.do">
									<bean:message key="static.mainpage.view" /> <br /> <br />
								</html:link>
							</li>
						</logic:equal>
						<logic:equal name="mainForm" property="peopleIsEmployee" value="true">
							<li>
								<html:link action="/resumeLook.do">
									<bean:message key="static.mainpage.view" /> <br /> <br />
								</html:link>
							</li>
							<li>
								<html:link action="/taskCondition.do">
									<bean:message key="static.mainpage.taskConditions" /> <br /> <br />
								</html:link>
							</li>
							<li>
								<html:link action="/solveTask.do">
									<bean:message key="static.mainpage.solveTask" /> <br /> <br />
								</html:link>
							</li>
						</logic:equal>
						<li>
							<html:link href="Help.jsp">
								<bean:message key="static.mainpage.help" /> <br />
							</html:link>
						</li>				
					</ul>					
				</fieldset>
				<br />
				
				<fieldset>
					<legend>
						<bean:message key="profile"/><bean:message key="colon"/> 
						<bean:write name="mainForm" property="userName"/>
					</legend>
					
					<ul class="menu_">
						<li>
							<html:link action="/message.do">
								<bean:message key="static.mainpage.message" /> (in: <bean:write name="mainForm" property="received"/> | out: <bean:write name="mainForm" property="sent"/>)								
							</html:link>  <br />
						</li>
						<li>
							<html:link action="/profile.do">
								<bean:message key="modify.edit.profile" /> <br /> <br />
							</html:link>
						</li>
						<li>
							<html:link href="index.jsp">
								<bean:message key="static.mainpage.exit" />
							</html:link>
						</li>
					</ul>
				</fieldset>
				</td>
				</tr>
				</table>
			</td>
			<td width="75%" valign="top"> 
				<html:form action="/main.do" method="post">
				<table width="100%" border="0" class="textValue_">
					<tr>
						<td align="right">
							<p class="title_">
								<bean:message key="newChapter"/><bean:message key="colon"/>
							</p>
						</td>
						<td align="right">
							<label><bean:message key="bean.forum.chapterName"/><bean:message key="colon"/></label>
						</td>
						<td>
							<html:text size="30" maxlength="150" name="mainForm" property="newForumName" />	
							<span class="errorLow_"> <html:errors property="error.forum.name.add" /> </span>
						</td>	
					</tr>
					<tr>
						<td></td>
						<td align="right">
							<label><bean:message key="bean.forum.chapterPassword"/><bean:message key="colon"/></label>
						</td>
						<td>
							<html:password size="20" maxlength="20" name="mainForm" property="newForumPassword" />
							<span class="errorLow_"> <html:errors property="error.forum.password.add" /> </span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>
							<html:img src="css/img/down.jpg" alt="" border="0" align="absmiddle"/>
							<html:submit styleClass="submit-button" property="buttonAdd">
								<bean:message key="button.add"/>
							</html:submit>
							<span class="errorLow_"> <html:errors property="error.insert" /> </span>
						</td>
					</tr>
				</table>
				</html:form>
				<br />
								
								
				<logic:empty name="mainForm" property="forum">
					<bean:message key="error.noValue"/>
				</logic:empty>
				<logic:notEmpty name="mainForm" property="forum">
					
					<table width=100% border=0>
						<tr>
							<td>
								<span class="title_">
									<bean:message key="bean.forum"/> <bean:message key="colon"/>
								</span>
							</td>
							<td width="60%" align="center">
								<bean:size id="size" name="mainForm" property="forum"/>
								<c:set var="totalSize" scope="request" value="${size}" />
								<c:set var="chunkSize" scope="request" value="${mainForm.chunkSize}" />
					
								<c:forEach var="selection" begin="1" end="${totalSize}" step="${chunkSize}">
						
									<jsp:useBean id="params" class="java.util.HashMap" />
									<c:set target="${params}" property="start" value="${selection - 1}" />
									<c:set target="${params}" property="chunkSize" value="${chunkSize}" />
							
									<c:set var="tableScrollClass" value="tableScroll_" />
									<c:if test="${mainForm.start == (selection-1)}">
            							<c:set var="tableScrollClass" value="tableScrollSelected_" />
            						</c:if>
            			
            						<span class="<c:out value='${tableScrollClass}' />">
										<html:link action="main.do" name="params">
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
									<html:form action="/main.do" method="post">
										<bean:message key="showOn"/>
										<html:text styleClass="chunkSize_" name="mainForm" property="chunkSize" size="4"/>
										<bean:message key="strings"/>
									</html:form>
								</div>
							</td>
						</tr>
					</table> <br />
					
					
					<!-- Table with topics for administrator -->
					<logic:equal name="mainForm" property="peopleIsAdmin" value="true">
						<html:form action="/main.do" method="post">
					
							<table width="100%" border="1" cellpadding="3" cellspacing="0">
								<tr>
									<th class="tableHeader_"> # </th>
									<th class="tableHeader_"> <bean:message key="bean.forum.date"/> </th>
									<th class="tableHeader_"> <bean:message key="bean.forum.chapterName"/> </th>
									<th class="tableHeader_"> <bean:message key="bean.forum.chapterPassword"/> </th>
									<th class="tableHeader_"> <bean:message key="created"/> </th>
								</tr>
							
								<c:set var="start" scope="request" value="${mainForm.start}" />
								<c:set var="finish" scope="request" value="${start + chunkSize - 1}" />
							
								<c:forEach var="loop" items="${mainForm.forum}" 
											varStatus="current" begin="${start}" end="${finish}">
								
									<c:set var="tdclass" value="evenRow_" />
									<c:if test="${current.count % 2 == 0}">
            							<c:set var="tdclass" value="oddRow_" />
          							</c:if>
            					
            						<tr> 
           								<td width="15" align="center" class='<c:out value="${tdclass}" />'>           							
											<html:multibox styleClass="checkBox_" name="mainForm" property="selectedItems">
												<bean:write name="loop" property="id_Chapter"/>
											</html:multibox>									
            							</td>
            						
            							<td width="90" align="center" class='<c:out value="${tdclass}" />'>
            								<bean:write name="loop" property="chapterDate"/>
            							</td>
            							
            							<td align="center" class='<c:out value="${tdclass}" />'>
            								<span class="menu_">
            									<a href='<c:url value="/forumChapter.do">
            												<c:param name="id_Chapter" value="${loop.id_Chapter}" />
            											 </c:url>'>
            										 <bean:write name="loop" property="chapterName"/>
            									</a>
            								</span>
            							</td>
            							
            							<td width="15%" align="center" class='<c:out value="${tdclass}" />'>
            								<bean:write name="loop" property="chapterPassword"/>
            							</td>
            						
            							<td width="20%" align="center" class='<c:out value="${tdclass}" />'>
            								<bean:write name="loop" property="login"/>
            							</td>
           							</tr>
           	 					</c:forEach>
							</table> <br />
						
							<html:submit property="buttonDelete">
								<bean:message key="button.delete.selected"/>
							</html:submit>
							<span class="errorLow_"> <html:errors property="error.delete" /> </span> <br />
						
						</html:form>
					</logic:equal>
					
					<!-- Table with topics for rest -->
					<logic:equal name="mainForm" property="peopleIsAdmin" value="false">					
						<table width="100%" border="1" cellpadding="3" cellspacing="0">
							<tr>
								<th class="tableHeader_"> <bean:message key="bean.forum.date"/> </th>
								<th class="tableHeader_"> <bean:message key="bean.forum.chapterName"/> </th>
								<th class="tableHeader_"> <bean:message key="bean.forum.chapterPassword"/> </th>
								<th class="tableHeader_"> <bean:message key="created"/> </th>
							</tr>
						
							<c:set var="start" scope="request" value="${mainForm.start}" />
							<c:set var="finish" scope="request" value="${start + chunkSize - 1}" />
						
							<c:forEach var="loop" items="${mainForm.forum}" 
										varStatus="current" begin="${start}" end="${finish}">
							
								<c:set var="tdclass" value="evenRow_" />
								<c:if test="${current.count % 2 == 0}">
            						<c:set var="tdclass" value="oddRow_" />
          						</c:if>
            				
            					<tr> 
            						<td width="90" align="center" class='<c:out value="${tdclass}" />'>
            							<bean:write name="loop" property="chapterDate"/>
            						</td>
            						
            						<td align="center" class='<c:out value="${tdclass}" />'>
            							<span class="menu_">
            								<bean:write name="loop" property="chapterName"/>
            							</span>
            						</td>
            						
            						<td width="15%" align="center" valign="middle" class='<c:out value="${tdclass}" />'>
            							<html:form action="/forumAutho.do" method="post">
            								<html:hidden property="chapId" value="${loop.id_Chapter}"/> 
            								<html:password size="10" property="chapPass"/>
            							</html:form>
            						</td>
            					
            						<td width="12%" align="center" class='<c:out value="${tdclass}" />'>
            							<bean:write name="loop" property="login"/>
            						</td>
           						</tr>
           	 				</c:forEach>
						</table> <br />		
					</logic:equal>
					
					</logic:notEmpty>
			</td>
		</tr>
	</table>	
</body>
</html:html>