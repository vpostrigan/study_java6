<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ForumChapter.jsp -->

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
				<span class="address_"> <bean:write name="forumChapterForm" property="chapterName"/> </span>
			</td>
		</tr>	
	</table>
	<br />
	
	<!-- Comment list -->
	<logic:empty name="forumChapterForm" property="comment">
		<bean:message key="error.noValue"/>
	</logic:empty>
	<logic:notEmpty name="forumChapterForm" property="comment">
	
		<table width=100% border=0>
			<tr>
				<td width="70%" align="center">
					<bean:size id="size" name="forumChapterForm" property="comment"/>
					<c:set var="totalSize" scope="request" value="${size}" />
					<c:set var="chunkSize" scope="request" value="${forumChapterForm.chunkSize}" />
					
					<c:forEach var="selection" begin="1" end="${totalSize}" step="${chunkSize}">
						
						<jsp:useBean id="params" class="java.util.HashMap" />
						<c:set target="${params}" property="start" value="${selection - 1}" />
						<c:set target="${params}" property="chunkSize" value="${chunkSize}" />
						<c:set target="${params}" property="id_Chapter" value="${forumChapterForm.id_Chapter}" />
						
						<c:set var="tableScrollClass" value="tableScroll_" />
						<c:if test="${forumChapterForm.start == (selection-1)}">
            				<c:set var="tableScrollClass" value="tableScrollSelected_" />
            			</c:if>
            			
            			<span class="<c:out value='${tableScrollClass}' />">
						<html:link action="forumChapter.do" name="params">
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
						<html:form action="/forumChapter.do" method="post">
							<bean:message key="showOn"/>
							<html:text styleClass="chunkSize_" name="forumChapterForm" property="chunkSize" size="4"/>
							<bean:message key="strings"/>
							
							<html:hidden name="forumChapterForm" property="id_Chapter"/>
						</html:form>
					</div>
				</td>
			</tr>
		</table> <br />
		
	<table width=100% border=1 cellpadding=3 cellspacing=0>
				
		<c:set var="start" scope="request" value="${forumChapterForm.start}" />
		<c:set var="finish" scope="request" value="${start + forumChapterForm.chunkSize - 1}" />
		
		<c:forEach var="loop" items="${forumChapterForm.comment}" 
					varStatus="current" begin="${start}" end="${finish}">
					
			<c:set var="tdclass" value="evenRow_" />
			<c:if test="${current.count % 2 == 0}">
        		<c:set var="tdclass" value="oddRow_" />
          	</c:if>
            		
			<tr>
           		<td class='evenRow_'>
           		
           		<table width=100% border=0>
           			<tr>
           				<td class="textValueForum_" width="115">
           					<bean:write name="loop" property="login"/>
           				</td>
           				<td align="right">
           					
           					<span class="menu_">
           						<html:link action="/message.do?newMessage=true">
           							<html:img src="css/img/messageAddLittle.jpg"
           								alt="New message" border="0" align="absmiddle" />
           						</html:link>
           					</span>
           					
           					<bean:message key="bean.forum.date"/><bean:message key="colon"/>
           						<bean:write name="loop" property="commentDate"/>
           				</td>
           				<td width="5%" align="right">
           					# <bean:write name="loop" property="commentNumber"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="left" valign="top">
           					<img src="<bean:write name='loop' property='avatar'/>" 
           						alt="" align="top" border="0" height="100" width="100" />
           				</td>
           				<td colspan=2 align="left" valign="top" class='<c:out value="${tdclass}" />'>
           					<div class="textValueForum_">
           						<bean:write name="loop" property="commentValue"  filter="false"/>
           					</div>
           				
           				<logic:notEmpty name="loop" property="dataObject">
           					<br /><br />
           					<fieldset class="textValue_">
								<legend>
									<span class="textValue_"> 
										<bean:message key="bean.message.attachments"/><bean:message key="colon"/> 
									</span>
								</legend><br />
           						
           						<logic:iterate id="subLoop" name="loop" property="dataObject">
           							           							
           							<span class="menu_">
           								#<bean:write name="subLoop" property="id_Object"/>: &nbsp;
            							<a href='<c:out value="${subLoop.object}"/>'>
											<bean:write name="subLoop" property="objectName"/>
										</a>
            						</span>
           							
           							<br />
           						</logic:iterate>
           					</fieldset> 
           					</logic:notEmpty> 				
           				</td>
           			</tr>           			
           		</table> 
           		
            	</td>
			</tr>
		</c:forEach>
	</table> <br />
	
	<table width=100% border=0>
		<tr>
			<td width="100%" align="center">
				
				<c:forEach var="selection" begin="1" end="${totalSize}" step="${chunkSize}">
					
					<c:set target="${params}" property="start" value="${selection - 1}" />
					<c:set target="${params}" property="chunkSize" value="${chunkSize}" />
					<c:set target="${params}" property="id_Chapter" value="${forumChapterForm.id_Chapter}" />
					
					<c:set var="tableScrollClass" value="tableScroll_" />
					<c:if test="${forumChapterForm.start == (selection-1)}">
           				<c:set var="tableScrollClass" value="tableScrollSelected_" />
           			</c:if>
           			
           			<span class="<c:out value='${tableScrollClass}' />">
					<html:link action="forumChapter.do" name="params">
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
			</td>
		</tr>
	</table>
	
	</logic:notEmpty>
	
	<br />
	<html:form action="/forumChapter.do" method="post">
		<p class="title_">
			<bean:message key="modify.newComment"/><bean:message key="colon"/>
		</p>
		
		<label class="myLabel_"> <bean:message key="bean.comment.CommentValue"/><bean:message key="colon"/> </label>
			<html:textarea rows="8" cols="100" onkeypress="return imposeMaxLength(this, 4999);" 
							property="newComment.commentValue"/> 
							
		<html:submit styleClass="submit-button" property="buttonAdd">
			<bean:message key="button.add"/>
		</html:submit>
		<span class="errorLow_"> <html:errors property="error.insert" /> </span> <br />
		
		<logic:notEmpty name="forumChapterForm" property="newComment.dataObject">
			<label class="myLabel_"> <bean:message key="bean.message.attachments"/><bean:message key="colon"/> </label>
			
			<logic:iterate id="subLoop" name="forumChapterForm" property="newComment.dataObject">
				
				<span class="menu_">
            		<a href='<c:out value="${subLoop.object}"/>'>
						<bean:write name="subLoop" property="objectName"/>
					</a>
            	</span> <br />
			</logic:iterate>
		</logic:notEmpty>
		
		<html:hidden name="forumChapterForm" property="id_Chapter"/>
		<html:hidden name="forumChapterForm" property="id_Object"/>
		<html:hidden name="forumChapterForm" property="object"/>
		<html:hidden name="forumChapterForm" property="objectName"/>
	</html:form>
	
	<html:form action="/forumChapter.do" method="post" enctype="multipart/form-data">
		<html:hidden name="forumChapterForm" property="id_Chapter"/>
		<label class="myLabel_">
			<bean:message key="bean.message.attachments"/>(<bean:message key="fileSize"/>)<bean:message key="colon"/>
		</label>
		<html:file property="file" />
		
		<html:submit property="buttonUpload">
			<bean:message key="button.upload"/>
		</html:submit>
		<span class="errorLow_"> <html:errors property="error.file" /> </span> <br />
	</html:form>
	
</body>
</html:html>