<%--
/* *****************************************************************************
 * 22.07.2008
 *
 * (C) Eclipse SP LLC. All rights reserved
 *
 * Home page
 * 
 * $Revision: 839 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-13 12:52:01 +0300 (Ср, 13 авг 2008) $
 * ****************************************************************************/
--%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" session="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="elements/header.jsp"/>
	
	<div class="modifyForm_">	
	
		<div class="editHeader">			
			<fmt:message key="tournamentEdit.title" />	
		</div>
								
		<form:form name="editTF" action="editTournament.html" method="post" 
									commandName="editTournament" modelAttribute="editTournament">			
			<div>
			
			<label for="tournamentName"><fmt:message key="tournamentEdit.tournamentName" />*:</label>
				<form:input path="tournamentName"/>
			<br/>
			
			<label for="gameKind"><fmt:message key="tournamentEdit.gameKind" />*:</label>
				<form:input path="gameKind"/>
			<br/>
			
			<label for="viewStartDate"><fmt:message key="tournamentEdit.startDate" />*:</label>
				<form:input path="viewStartDate"/>
				<input type="button" value=" ... " onclick="showCalender(document.editTF.viewStartDate)"/>
			<br/>
			
			<label for="viewFinishDate"><fmt:message key="tournamentEdit.finishDate" />*:</label>
				<form:input path="viewFinishDate"/>
				<input type="button" value=" ... " onclick="showCalender(document.editTF.viewFinishDate)"/>
			<br/>		
			
			<label for="visibility"><fmt:message key="tournamentEdit.visibility" />*:</label>		
			<form:select path="visibility" multiple="false">
				<option value="Public"><fmt:message key="public" /></option>
        		<option value="Private"><fmt:message key="private" /></option>
			</form:select>
			<br/>
			
			<label for="description"><fmt:message key="tournamentEdit.description" />:</label>
				<form:textarea id="description" rows="3" cols="50" path="description"/>
			<br/><br/>
		
			<button id="firstButton" class="send_button3" type="submit"  name="action" value="Accept" onclick="this.form.submit();">Accept</button> 
			<button class="send_button3" type="submit"  name="action" value="Cancel" onclick="this.form.submit();">Cancel</button> 
						
			<div class="errors">
				<form:errors path="*" cssClass="errorMessage"/>
			</div>
			</div>	
		</form:form>		
	</div>

<div id="calenderS" class="calShadow"></div>
<div id="calender" class="calMain">
<div id="calenderH" class="calH">
	<span style="position:absolute; top:1px; left:3px; font-size:12px; height:13px;">Calender</span>
	<img src="/com.eclipsesp.tplanner.web/images/popupCalendar/close.jpg" border="none" width=13 height=13 onmousedown="hideCalender(false)">
</div>

<table cellpadding=0 border=0 cellspacing=5 width="100%" class="spanText"><tr align="center">
	<td class="calMonthYear"><img src="/com.eclipsesp.tplanner.web/images/popupCalendar/back.jpg" border="none" width=13 height=13 onmousedown="setMonth(false)"></td>
	<td class="calMonthYear" width="70"><span id="calender_m"></span></td>
	<td class="calMonthYear"><img src="/com.eclipsesp.tplanner.web/images/popupCalendar/next.jpg" border="none" width=13 height=13 onmousedown="setMonth(true)"></td>
	<td class="calMonthYear">  </td>
	<td class="calMonthYear"><img src="/com.eclipsesp.tplanner.web/images/popupCalendar/back.jpg" border="none" width=13 height=13 onmousedown="setYear(false)"></td>
	<td class="calMonthYear" width="70"><span id="calender_y" class="calMonthYear"></span></td>
	<td class="calMonthYear"><img src="/com.eclipsesp.tplanner.web/images/popupCalendar/next.jpg" border="none" width=13 height=13 onmousedown="setYear(true)"></td>
</tr></table>
<table cellpadding=0 border=0 cellspacing=0>
<tr align="center">
	<td><div class="calHdr">S</div></td>
	<td><div class="calHdr">M</div></td>
	<td><div class="calHdr">T</div></td>
	<td><div class="calHdr">W</div></td>
	<td><div class="calHdr">T</div></td>
	<td><div class="calHdr">F</div></td>
	<td><div class="calHdr">S</div></td>
</tr><tr align="center">
	<td><div id="cal1" class="calText"> </div></td>
	<td><div id="cal2" class="calText"> </div></td>
	<td><div id="cal3" class="calText"> </div></td>
	<td><div id="cal4" class="calText"> </div></td>
	<td><div id="cal5" class="calText"> </div></td>
	<td><div id="cal6" class="calText"> </div></td>
	<td><div id="cal7" class="calText"> </div></td>
</tr><tr align="center">
	<td><div id="cal8" class="calText"> </div></td>
	<td><div id="cal9" class="calText"> </div></td>
	<td><div id="cal10" class="calText"> </div></td>
	<td><div id="cal11" class="calText"> </div></td>
	<td><div id="cal12" class="calText"> </div></td>
	<td><div id="cal13" class="calText"> </div></td>
	<td><div id="cal14" class="calText"> </div></td>
</tr><tr align="center">
	<td><div id="cal15" class="calText"> </div></td>
	<td><div id="cal16" class="calText"> </div></td>
	<td><div id="cal17" class="calText"> </div></td>
	<td><div id="cal18" class="calText"> </div></td>
	<td><div id="cal19" class="calText"> </div></td>
	<td><div id="cal20" class="calText"> </div></td>
	<td><div id="cal21" class="calText"> </div></td>
</tr><tr align="center">
	<td><div id="cal22" class="calText"> </div></td>
	<td><div id="cal23" class="calText"> </div></td>
	<td><div id="cal24" class="calText"> </div></td>
	<td><div id="cal25" class="calText"> </div></td>
	<td><div id="cal26" class="calText"> </div></td>
	<td><div id="cal27" class="calText"> </div></td>
	<td><div id="cal28" class="calText"> </div></td>
</tr><tr align="center">
	<td><div id="cal29" class="calText"> </div></td>
	<td><div id="cal30" class="calText"> </div></td>
	<td><div id="cal31" class="calText"> </div></td>
	<td><div id="cal32" class="calText"> </div></td>
	<td><div id="cal33" class="calText"> </div></td>
	<td><div id="cal34" class="calText"> </div></td>
	<td><div id="cal35" class="calText"> </div></td>
</tr><tr align="center">
	<td><div id="cal36" class="calText"> </div></td>
	<td><div id="cal37" class="calText"> </div></td>
	<td><div id="cal38" class="calText"> </div></td>
	<td><div id="cal39" class="calText"> </div></td>
	<td><div id="cal40" class="calText"> </div></td>
	<td><div id="cal41" class="calText"> </div></td>
	<td><div id="cal42" class="calText"> </div></td>
</tr><tr><td colspan=7 align="left"><div id="calMsg" class="calMonthYear"> </div></td></tr>
</table>
</div>

<jsp:include page="elements/footer.jsp"/>