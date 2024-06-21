<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
				<span class="address_"> <bean:message key="static.mainpage.profile"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	<table width=100% border=0>
		<tr>
			<td width=60%> 
			<html:form action="/profile.do" method="post">
			
				<label><bean:message key="bean.people.surname"/><bean:message key="colon"/></label>
					<html:text maxlength="25" name="profileForm" property="people.surname" />
					<span class="errorLow_"> <html:errors property="error.people.surname.edit" /> </span> <br />
					
				<label><bean:message key="bean.people.name"/><bean:message key="colon"/></label> 
					<html:text maxlength="25" name="profileForm" property="people.name"/>
					<span class="errorLow_"> <html:errors property="error.people.name.edit" /> </span> <br />
				
				<label><bean:message key="bean.people.patronymic"/><bean:message key="colon"/></label>
					<html:text maxlength="30" name="profileForm" property="people.patronymic"/>
					<span class="errorLow_"> <html:errors property="error.people.patronymic.edit" /> </span> <br />
					
				<label><bean:message key="bean.people.login"/><bean:message key="colon"/></label> 
					<html:text maxlength="20" name="profileForm" property="people.login"/>
					<span class="errorLow_"> <html:errors property="error.people.login.edit" /> </span> <br />
				
				<label><bean:message key="bean.people.password"/><bean:message key="colon"/></label>
					<html:password maxlength="20" name="profileForm" property="people.password"/>
					<span class="errorLow_"> <html:errors property="error.people.password.edit" /> </span> <br />
				
				<label><bean:message key="bean.people.peopleType"/><bean:message key="colon"/></label>
					<html:text disabled="true" name="profileForm" property="people.people_type"/> <br />
					
				<label><bean:message key="bean.people.age"/><bean:message key="colon"/></label>
					<html:text maxlength="4" name="profileForm" property="people.age"/>
					<span class="errorLow_"> <html:errors property="error.people.age.edit" /> </span> <br />
				
				<label><bean:message key="bean.people.address"/><bean:message key="colon"/></label>
					<html:text size="40" name="profileForm" property="people.address"/>
					<span class="errorLow_"> <html:errors property="error.people.address.edit" /> </span> <br />
					
				<html:submit property="buttonEdit" styleClass="submit-button">
					<bean:message key="button.edit"/>
				</html:submit>
				<span class="errorLow_"> <html:errors property="error.edit" /> </span>
				
				<logic:equal name="profileForm" property="stringEdited" value="true">
					<html:img src="css/img/action_success.jpg" alt="" border="0" align="absmiddle" />
					<bean:message key="modify.edited"/> <br />
				</logic:equal>
				
			</html:form>
			</td>
			<td align=right valign=top> 
				<span class="title_"><bean:message key="bean.people.avatar"/><bean:message key="colon"/></span>
				<img src="<bean:write name='profileForm' property='people.avatar'/>" alt="" align="top" border="0" height="100" width="100" /><br />
				
				<html:form action="/profile.do" method="post" enctype="multipart/form-data">
					<html:file property="file" /><br /><br />
		
					<html:submit property="buttonUpload">
						<bean:message key="button.upload.change"/>
					</html:submit>
					<span class="errorLow_"> <html:errors property="error.file" /> </span> <br />
				</html:form>
			</td>
		</tr> 
	</table> <hr />
	
	<!-- Phone -->
	<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.peoplePhone.phone"/><bean:message key="colon"/> </label>	
				
				<table border=0>
					<html:form action="/profile.do" method="post">
						<logic:notEmpty name="profileForm" property="peoplePhone">	
							<logic:iterate id="phone" name="profileForm" property="peoplePhone">
								<tr>
									<td colspan=3 class="textValue_">
										<html:multibox name="profileForm" property="selectedItemsPhone">
											<bean:write name="phone" property="phone"/>
										</html:multibox>
																				
										<bean:write name="phone" property="phone"/> <br />														
									</td>	
								</tr>
							</logic:iterate>							
						</logic:notEmpty>
								
								<tr>
									<td>
										<html:submit styleClass="select-button" property="buttonDeletePhone">
											<bean:message key="button.delete.selected"/>
										</html:submit>
										<span class="errorLow_"> <html:errors property="error.phone.delete" /> </span> <br />
									</td>
									<td width="80"></td>
									<td>
										<label> <bean:message key="modify.newPhone"/><bean:message key="colon"/> </label>
									</td>
									<td>
										<html:text maxlength="20" property="addPhone" />
										
										<html:submit styleClass="select-button" property="buttonAddPhone">
											<bean:message key="button.add"/>
										</html:submit>
										<span class="errorLow_"> <html:errors property="error.phone.add" /> </span>
									</td>
								</tr>
					</html:form>
				</table>
			</td>
		</tr>
	</table> <hr />
	
	<!-- Email -->
	<table width=100% border=0>
		<tr>
			<td width=60%>
				<label> <bean:message key="bean.peopleEmail.email"/><bean:message key="colon"/> </label>
	
				<table border=0>
					<html:form action="/profile.do" method="post">
						<logic:notEmpty name="profileForm" property="peopleEmail">	
							<logic:iterate id="pEmail" name="profileForm" property="peopleEmail">
								<tr>
									<td colspan=3 class="textValue_">
										<html:multibox styleClass="checkBox_" name="profileForm" property="selectedItemsEmail">
											<bean:write name="pEmail" property="email"/>
										</html:multibox>
										
										<span class="textValue_">
											<bean:write name="pEmail" property="email"/>
										</span> <br />										
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
							
								<tr>
									<td>
										<html:submit styleClass="select-button" property="buttonDeleteEmail">
											<bean:message key="button.delete.selected"/>
										</html:submit>
										<span class="errorLow_"> <html:errors property="error.email.delete" /> </span> <br />
									</td>
									<td width="80"></td>
									<td>
										<label> <bean:message key="modify.newEmail"/><bean:message key="colon"/> </label>
									</td>
									<td>
										<html:text maxlength="50" property="addEmail" />
										
										<html:submit styleClass="select-button" property="buttonAddEmail">
											<bean:message key="button.add"/>
										</html:submit>
										<span class="errorLow_"> <html:errors property="error.email.add" /> </span>
									</td>
								</tr>
					</html:form>
				</table>
				
			</td>
		</tr>
	</table> <hr />
	
	<!-- Employment -->
	<table width="100%" border="0">
		<tr>
			<td width="60%">
				<label> <bean:message key="bean.peopleEmployment.employment"/><bean:message key="colon"/> </label>
	
				<table border="0">
					<html:form action="/profile.do" method="post">
						<logic:notEmpty name="profileForm" property="peopleEmployment">
							<logic:iterate id="pEmployment" name="profileForm" property="peopleEmployment">
								<tr>
									<td colspan="3" class="textValue_">
										<html:multibox styleClass="checkBox_" name="profileForm" property="selectedItemsEmployment">
											<bean:write name="pEmployment" property="employment"/>
										</html:multibox>
									
										<span class="textValue_">
											<bean:write name="pEmployment" property="employment"/>
										</span> <br />										
									</td>			
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						
								<tr>
									<td>
										<html:submit styleClass="select-button" property="buttonDeleteEmployment">
											<bean:message key="button.delete.selected"/>
										</html:submit>
										<span class="errorLow_"><html:errors property="error.employment.delete" /></span><br />
									</td>
									<td width="80"></td>
									<td>
										<label> <bean:message key="modify.newWork"/><bean:message key="colon"/> </label>
									</td>
									<td>
										<html:text maxlength="50" property="addEmployment" />
										
										<html:submit styleClass="select-button" property="buttonAddEmployment">
											<bean:message key="button.add"/>
										</html:submit>
										<span class="errorLow_"> <html:errors property="error.employment.add" /> </span>
									</td>
								</tr>
					</html:form>
				</table>
				
			</td>
		</tr>
	</table> <hr />
	
</body>
</html:html>