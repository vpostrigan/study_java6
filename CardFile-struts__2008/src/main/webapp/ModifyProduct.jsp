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
	
		<!-- Таблица стилей из %css/Main.css -->
	<link rel="stylesheet" type="text/css" href="css/ModifyProduct.css" />
	
	<title>
		<bean:message key="title"/>
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
	
	<p>
		<html:link action="main.do">	
			<bean:message key="forward.mainPage"/>		
		</html:link>
	</p>
	
	
	
	<!-- Добавление новой записи в таблицу с продуктами -->
	
	<div class="modifyProductForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/addd.png" alt="" border="0" align="absmiddle" />
			<bean:message key="ProductTable.newRecord" />
		</legend>
		<html:form action="addProduct.do" method="post">	
				
			<label for="ID code"><bean:message key="code"/><bean:message key="colon"/></label>
			<html:text size="5" property="code" />
			<html:errors property="error.code.add" /> <br />				
				
			<label for="productName"><bean:message key="productName"/><bean:message key="colon"/></label>
			<html:text maxlength="100" size="60" property="productName" />
			<html:errors property="error.productName.add" /> <br />
			
			<label for="description"><bean:message key="decription"/><bean:message key="colon"/></label>			
			<html:textarea styleClass="textDescription" rows="3" cols="52" onkeypress="return imposeMaxLength(this, 254);" property="description"/>
			<br /> 
			
			<label for="price"><bean:message key="price"/><bean:message key="colon"/></label>
			<html:text size="7" property="price"/>
			<html:errors property="error.price.add" /> <br />
			
			<label for="category"><bean:message key="category"/><bean:message key="colon"/></label>			
			<logic:empty name="addProductForm" property="categoryTableValue">
				<bean:message key="error.noValue"/>
			</logic:empty>			
			<logic:notEmpty name="addProductForm" property="categoryTableValue">
				<html:select property="category">
					<html:optionsCollection name="addProductForm" property="categoryTableValue" 
												value="idCategory" label="categoryName"/>
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
	
	<logic:equal name="addProductForm" property="stringInserted" value="true">
		<html:img src="css/img/action_success.png" alt="" border="0" align="absmiddle" />
		<bean:message key="product.quote"/> 
		<span class="addStr_"><c:out value="${requestScope.code_add}"/></span> <bean:message key="comma"/>
		<span class="addStr_"><c:out value="${requestScope.productName_add}"/></span>
		<bean:message key="added.quote"/> <br />
	</logic:equal>
	
	
	
	<!-- Удаление записи из таблицы с продуктами -->
	
	<div class="modifyProductForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/remove.png" alt="" border="0" align="absmiddle" />
			<bean:message key="ProductTable.deleteRecord" />
		</legend>
		<html:form action="deleteProduct.do" method="post">
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
	
	<logic:equal name="deleteProductForm" property="stringDeleted" value="true">
		<html:img src="css/img/action_success.png" alt="" border="0" align="absmiddle" />
		<bean:message key="product.quote"/>  
		<span class="addStr_"><c:out value="${requestScope.code_delete}"/></span> 
		<bean:message key="deleted.quote"/> <br />
	</logic:equal>
	
	
	
	<!-- Редактирование записи из таблицы с продуктами -->
	
	<div class="modifyProductForm_"><br />
		<fieldset>
		<legend>
			<html:img src="css/img/edit.png" alt="" border="0" align="absmiddle" />
			<bean:message key="ProductTable.editRecord" />
		</legend>
		
		<html:form action="editProduct.do" method="post">
			
			<label for="ID code"><bean:message key="code"/><bean:message key="colon"/></label>
			<html:text size="5" property="code" />
			<html:errors property="error.code.edit" /> 
			
			<html:submit property="buttonShow">
				<bean:message key="button.showProduct"/>
			</html:submit>
			<html:errors property="error.code.edit.noValueForShow" /> <br /> 			
				
			<label for="productName"><bean:message key="productName"/><bean:message key="colon"/></label>
			<html:text maxlength="100" size="60" property="productName" />
			<html:errors property="error.productName.edit" /> <br />
			
			<label for="description"><bean:message key="decription"/><bean:message key="colon"/></label>			
			<html:textarea styleClass="textDescription" rows="3" cols="52" onkeypress="return imposeMaxLength(this, 254);" property="description"/>
			<br /> 
			
			<label for="price"><bean:message key="price"/><bean:message key="colon"/></label>
			<html:text size="7" property="price"/>
			<html:errors property="error.price.edit" /> <br />
			
			<label for="category"><bean:message key="category"/><bean:message key="colon"/></label>			
			<logic:empty name="editProductForm" property="categoryTableValue">
				<bean:message key="error.noValue"/>
			</logic:empty>			
			<logic:notEmpty name="editProductForm" property="categoryTableValue">
				<html:select property="category">
					<html:optionsCollection name="editProductForm" property="categoryTableValue" 
												value="idCategory" label="categoryName"/>
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
		
	<logic:equal name="editProductForm" property="stringEdited" value="true">
		<html:img src="css/img/action_success.png" alt="" border="0" align="absmiddle" />
		<bean:message key="product.quote"/> 
		<span class="addStr_"><c:out value="${requestScope.code_edit}"/></span> <bean:message key="comma"/>
		<span class="addStr_"><c:out value="${requestScope.productName_edit}"/></span> 
		<bean:message key="edited.quote"/> <br />
	</logic:equal>
	
	
	<p>
		<html:link action="main.do">	
			<bean:message key="forward.mainPage"/>			
		</html:link>
	</p>
</body>
</html:html>