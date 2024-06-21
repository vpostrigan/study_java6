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
	<link rel="stylesheet" type="text/css" href="css/Main.css" />
	
	<title>
		<bean:message key="title"/>
	</title>
</head>
<body>
	<p class="logo_">
		<bean:message key="title"/>
	</p>
	<div class="errorAttachment_">
		<html:errors property="error.db"/>
	</div>
					
	<html:form action="main.do" method="post">	
		<table width=100% border=0>
			<tr>
					<!-- ComboBox Категории продуктов -->
				<td class="title_" align=right>
					<bean:message key="category"/>
				</td>
				<td>
					
					<logic:notEmpty name="categoryTableValue">
						<html:select property="category">
							<html:optionsCollection name="categoryTableValue" 
													value="idCategory" label="categoryName"/>
						</html:select>
					</logic:notEmpty>
										
					<input type="submit" value="<bean:message key="show"/>"/>
				</td>
				
					<!-- Text строка поиска -->	
				<td align=right valign=top class="search_">
				</td>		
				<td>
					<div class="errorAttachment_">
						<html:errors property="chunkSize" />
					</div>
					
					<html:text styleClass="searchText_" property="search" />
					
				</td>
			</tr>
		</table>
	
		<br />
		
		<logic:empty name="productTableValue">
			<bean:message key="error.noValue"/>
		</logic:empty>
		
		<logic:notEmpty name="productTableValue">
		<table width=100% border=0>
		
			<tr>
				<td>
					<div class="title_">
						<bean:message key="products"/>
					</div>
				</td>
				<td width="60%" align="center">
				
					<c:set var="totalSize" scope="request" value="${productTableSize}" />
										
					
						<c:forEach var="selection" begin="1" end="${totalSize}" step="${requestScope.chunkSize}">
						
							<jsp:useBean id="params" class="java.util.HashMap" />						
							<c:set target="${params}" property="category" value="${requestScope.category}"/>
							<c:set target="${params}" property="search" value="${requestScope.search}"/>
							<c:set target="${params}" property="orderBy" value="${requestScope.orderBy}" />
							<c:set target="${params}" property="reverse" value="${requestScope.reverse}" />
							<c:set target="${params}" property="chunkSize" value="${requestScope.chunkSize}" />
							<c:set target="${params}" property="start" value="${selection - 1}" />
							
							<c:set var="tableScrollClass" value="tableScroll_" />
							<c:if test="${start == (selection-1)}">
            					<c:set var="tableScrollClass" value="tableScrollSelected_" />
            				</c:if>
            				<span class="<c:out value='${tableScrollClass}' />">
							<html:link action="main.do" name="params" >								
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
						<bean:message key="showOn"/>
						<html:text styleClass="chunkSize_" property="chunkSize" size="4"/>
						<bean:message key="strings"/>
					</div>
				</td>						
			</tr>
			
		</table>
		</logic:notEmpty>
		
			<logic:notEmpty name="productTableValue">
				<table width=100% border=0>	
				
				<!-- Таблица продуктов -->
				
				<tr>
					<td> 
						<table cellpadding="3" cellspacing="0" border=1 width=100%>
						
						
							<!-- Шапка таблицы продуктов -->
							
							<jsp:useBean id="paramsOrderBy" class="java.util.HashMap" />
							
							<c:set target="${paramsOrderBy}" property="category" value="${requestScope.category}"/>
							<c:set target="${paramsOrderBy}" property="search" value="${requestScope.search}"/>
							<c:set target="${paramsOrderBy}" property="chunkSize" value="${requestScope.chunkSize}" />							
							<c:set target="${paramsOrderBy}" property="start" value="${requestScope.start}" />
															
							<tr>
								<th>
									<table cellpadding="3" cellspacing="0" border="0">
										<tr>
											<td><bean:message key="code"/></td>
											<td width="13" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="code" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/A_Z.gif" alt="orderBy" border="0" />
												</html:link>
											</td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="code" />
												<c:set target="${paramsOrderBy}" property="reverse" value="code" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_Z_A.gif" border="0" />
												</html:link>
											</td>
										</tr>
									</table>											
								</th>
								<th>
									<table cellpadding=3 cellspacing=0 border=0>
										<tr>
											<td><bean:message key="productName"/></td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="productName" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_A_Z.gif" border="0" />
												</html:link>
											</td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="productName" />
												<c:set target="${paramsOrderBy}" property="reverse" value="productName" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_Z_A.gif" border="0" />
												</html:link>
											</td>
										</tr>
									</table>
								</th>								
								<th>
									<table cellpadding=3 cellspacing=0 border=0>
										<tr>
											<td><bean:message key="decription"/></td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="description" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_A_Z.gif" border="0" />
												</html:link>
											</td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="description" />
												<c:set target="${paramsOrderBy}" property="reverse" value="description" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_Z_A.gif" border="0" />
												</html:link>
											</td>
										</tr>
									</table>
								</th>							
								<th>
									<table cellpadding=3 cellspacing=0 border=0>
										<tr>
											<td><bean:message key="price"/></td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="price" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_A_Z.gif" border="0" />
												</html:link>
											</td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="price" />
												<c:set target="${paramsOrderBy}" property="reverse" value="price" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_Z_A.gif" border="0" />
												</html:link>
											</td>
										</tr>
									</table>							
								</th>
								<th>
									<table cellpadding=3 cellspacing=0 border=0>
										<tr>
											<td><bean:message key="category"/></td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="category" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_A_Z.gif" border="0" />
												</html:link>
											</td>
											<td width="6" height="15">
												<c:set target="${paramsOrderBy}" property="orderBy" value="category" />
												<c:set target="${paramsOrderBy}" property="reverse" value="category" />
												<html:link action="main.do" name="paramsOrderBy">
													<html:img src="css/img/arrow_Z_A.gif" border="0" />
												</html:link>
											</td>
										</tr>
									</table>
								</th>							
							</tr>   			
            				    
            						<!-- Тело таблицы продуктов -->
							
								<c:set var="start" scope="request" value="${requestScope.start}" />
								<c:set var="finish" scope="request" value="${start + requestScope.chunkSize - 1}" />
								
							<c:forEach var="loopProductTableValue" items="${productTableValue}" 
										varStatus="current" begin="${start}" end="${finish}">
						        			
            					<c:set var="tdclass" value="oddRow_" />
            					<c:if test="${current.count % 2 == 0}">
            						<c:set var="tdclass" value="evenRow_" />
            					</c:if>
            				<tr>
            					<td class='<c:out value="${tdclass}" />'>
            						<c:out value="${loopProductTableValue.code}" escapeXml="false"/>
            					</td>
            					<td class='<c:out value="${tdclass}" />'>
            						<c:out value="${loopProductTableValue.productName}" escapeXml="false"/>
            					</td>
            					<td class='<c:out value="${tdclass}" />'>
            						<c:out value="${loopProductTableValue.description}" escapeXml="false"/>
            					</td>
            					<td class='<c:out value="${tdclass}" />'>
            						<c:out value="${loopProductTableValue.price}" escapeXml="false"/>
            					</td>
            					<td class='<c:out value="${tdclass}" />'>
            						<c:out value="${loopProductTableValue.category}" escapeXml="false"/>
            					</td>
            				</tr>
            			</c:forEach>
            				
				</table>		
			</logic:notEmpty>
			
	</html:form>
	<p class="attachment_">
		<bean:message key="allProducts"/>
	</p>	
	
	<br />
	<div class="linkPath_">
		<html:img src="css/img/manipulate.png" alt="" border="0" align="absmiddle" />
		<html:link href="ModifyProduct.jsp">	
			<bean:message key="forward.manipulationWith.product"/>
		</html:link>
	</div>
</body>
</html:html>