<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- ModifyUser.jsp -->

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="css/img/Student.ico" />
	
		<!-- Таблица стилей из %css/Modify.css -->
	<link rel="stylesheet" type="text/css" href="css/Modify.css" />
	
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
				<html:link styleClass="address_" action="people.do">
					<bean:message key="static.mainpage.users"/>
				</html:link>
				<bean:message key="bigger"/>
				<span class="address_"> <bean:message key="static.mainpage.users.modifyUsers"/> </span>
			</td>
		</tr>
	</table>
	<br />
	
	
	
	<!-- Добавление новой записи в таблицу с продуктами -->
	
	<div class="modifyForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/addd.jpg" alt="" border="0" align="absmiddle" />
			<bean:message key="modify.user.newRecord" />
		</legend>
		<html:form action="addUser.do" method="post">
			
			<label for="code"><bean:message key="code"/><bean:message key="colon"/></label>
				<html:text size="5" property="people.id_People" />
				<html:errors property="error.code.add" /> <br />
			
			<label for="surname"><bean:message key="bean.people.surname"/><bean:message key="colon"/></label>
				<html:text maxlength="25" property="people.surname" />
				<html:errors property="error.people.surname.add" /> <br />
			
			<label for="name"><bean:message key="bean.people.name"/><bean:message key="colon"/></label>
				<html:text maxlength="25" property="people.name" />
				<html:errors property="error.people.name.add" /> <br />
			
			<label for="patronymic"><bean:message key="bean.people.patronymic"/><bean:message key="colon"/></label>
				<html:text maxlength="30" property="people.patronymic" />
				<html:errors property="error.people.patronymic.add" /> <br />
			
			<label for="login"><bean:message key="bean.people.login"/><bean:message key="colon"/></label>
				<html:text maxlength="20" property="people.login" />
				<html:errors property="error.people.login.add" /> <br />
			
			<label for="password"><bean:message key="bean.people.password"/><bean:message key="colon"/></label>
				<html:password maxlength="20" property="people.password" />
				<html:errors property="error.people.password.add" /> <br />
			
			<label for="age"><bean:message key="bean.people.age"/><bean:message key="colon"/></label>
				<html:text maxlength="4" property="people.age" />
				<html:errors property="error.people.age.add" /> <br />
			
			<label for="address"><bean:message key="bean.people.address"/><bean:message key="colon"/></label>
				<html:text size="35" maxlength="80" property="people.address" />
				<html:errors property="error.people.address.add" /> <br />
			
			<label for="peopleType"><bean:message key="bean.people.peopleType"/><bean:message key="colon"/></label>			
			<logic:empty name="addUserForm" property="peopleType">
				<bean:message key="error.noValue"/>
			</logic:empty>
			<logic:notEmpty name="addUserForm" property="peopleType">
				<html:select property="people.people_type">
					<html:optionsCollection name="addUserForm" property="peopleType" value="menuItem" label="url"/>
				</html:select>
			</logic:notEmpty>
			<br />
			
			<html:submit styleClass="submit-button">
				<bean:message key="button.add"/>
			</html:submit>
			<html:errors property="error.insert" />
		</html:form>		
		</fieldset>
	</div><br />
	
	<logic:equal name="addUserForm" property="stringInserted" value="true">
		<html:img src="css/img/action_success.jpg" alt="" border="0" align="absmiddle" />
		<bean:message key="modify.user.quote"/> 
			<span class="addStr_"><c:out value="${requestScope.code_add}"/></span> <bean:message key="comma"/>
			<span class="addStr_"><c:out value="${requestScope.name_add}"/></span>
		<bean:message key="modify.added.quote"/> <br />
	</logic:equal>
	
	
	
	<!-- Удаление записи из таблицы с продуктами -->
	
	<div class="modifyForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/remove.jpg" alt="" border="0" align="absmiddle" />
			<bean:message key="modify.user.deleteRecord" />
		</legend>
		
		<html:form action="deleteUser.do" method="post">
			<label for="ID code"><bean:message key="code"/><bean:message key="colon"/></label>
				<html:text size="5" property="code" />
				<html:errors property="error.code.delete" /> <br />
		
			<html:submit styleClass="submit-button">
				 <bean:message key="button.delete"/>
			</html:submit>
			<html:errors property="error.delete" />
		</html:form>
		
		</fieldset>
	</div><br />

	<logic:equal name="deleteUserForm" property="stringDeleted" value="true">
		<html:img src="css/img/action_success.jpg" alt="" border="0" align="absmiddle" />
		<bean:message key="modify.user.quote"/>  
			<span class="addStr_"><c:out value="${requestScope.code_delete}"/></span> 
		<bean:message key="modify.deleted.quote"/> <br />
	</logic:equal>
	
	
	
	<!-- Редактирование записи из таблицы с продуктами -->
	
	<div class="modifyForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/edit.jpg" alt="" border="0" align="absmiddle" />
			<bean:message key="modify.user.editRecord" />
		</legend>
		
		<html:form action="editUser.do" method="post">
			
			<label for="code"><bean:message key="code"/><bean:message key="colon"/></label>
				<html:text size="5" property="people.id_People" />
				<html:errors property="error.code.edit" /> 
			
			<html:submit property="buttonShow">
				<bean:message key="button.showRest"/>
			</html:submit>
			<html:errors property="error.code.noValueForShow.edit" /> <br /> 			
			
			<label for="surname"><bean:message key="bean.people.surname"/><bean:message key="colon"/></label>
				<html:text maxlength="25" property="people.surname" />
				<html:errors property="error.people.surname.edit" /> <br />
			
			<label for="name"><bean:message key="bean.people.name"/><bean:message key="colon"/></label>
				<html:text maxlength="25" property="people.name" />
				<html:errors property="error.people.name.edit" /> <br />
			
			<label for="patronymic"><bean:message key="bean.people.patronymic"/><bean:message key="colon"/></label>
				<html:text maxlength="30" property="people.patronymic" />
				<html:errors property="error.people.patronymic.edit" /> <br />
			
			<label for="login"><bean:message key="bean.people.login"/><bean:message key="colon"/></label>
				<html:text maxlength="20" property="people.login" />
				<html:errors property="error.people.login.edit" /> <br />
			
			<label for="password"><bean:message key="bean.people.password"/><bean:message key="colon"/></label>
				<html:password maxlength="20" property="people.password" />
				<html:errors property="error.people.password.edit" /> <br />
			
			<label for="age"><bean:message key="bean.people.age"/><bean:message key="colon"/></label>
				<html:text maxlength="4" property="people.age" />
				<html:errors property="error.people.age.edit" /> <br />
			
			<label for="address"><bean:message key="bean.people.address"/><bean:message key="colon"/></label>
				<html:text size="35" maxlength="80" property="people.address" />
				<html:errors property="error.people.address.edit" /> <br />
			
			<label for="peopleType"><bean:message key="bean.people.peopleType"/><bean:message key="colon"/></label>			
			<logic:empty name="editUserForm" property="peopleType">
				<bean:message key="error.noValue"/>
			</logic:empty>
			<logic:notEmpty name="editUserForm" property="peopleType">
				<html:select property="people.people_type">
					<html:optionsCollection name="editUserForm" property="peopleType" value="menuItem" label="url"/>
				</html:select>
			</logic:notEmpty>
			<br />
			
			<html:submit styleClass="submit-button" property="buttonEdit">
				 <bean:message key="button.edit"/>
			</html:submit>
			<html:errors property="error.edit" />
		</html:form>
		</fieldset>
	</div><br />
	
	<logic:equal name="editUserForm" property="stringEdited" value="true">
		<html:img src="css/img/action_success.jpg" alt="" border="0" align="absmiddle" />
		<bean:message key="modify.user.quote"/> 
			<span class="addStr_"><c:out value="${requestScope.code_edit}"/></span> <bean:message key="comma"/>
			<span class="addStr_"><c:out value="${requestScope.name_edit}"/></span> 
		<bean:message key="modify.edited.quote"/> <br />
	</logic:equal>
	
</body>
</html:html>