<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="elements/header.jsp" flush="true">
	<jsp:param name="selectedPage" value="/pages/index.jsp"/>
</jsp:include>

	<div id="subMenu">
		<label for="cities"><fmt:message key="header.jsp.city" />:</label>
		<select id="cities" size="1" onchange="setMapLocation('cities', 'tableLocation');">
			<c:forEach var="loop" items="${cities}" varStatus="current">
				<c:if test="${current.count == 1}">
					<option selected="selected" value="<c:out value="${loop.idString}"/>">
						<c:out value="${loop.name}"/>
						<c:set var="citySelected" value="${loop.idString}" scope="page" />
					</option>
				</c:if>
				<c:if test="${current.count != 1}">
					<option value="<c:out value="${loop.idString}"/>">
						<c:out value="${loop.name}"/>
					</option>
				</c:if>
			</c:forEach>
		</select>
		
		<label><a href="<c:url value="/pages/trips.jsp" />">
				<fmt:message key="header.jsp.trips" />
		</a></label>
		
		<label><a href="<c:url value="/journey.gwt.SesameGWT/SesameGWT.html" />">
				<fmt:message key="header.jsp.customization" />
		</a></label>
	</div>
	<div id="typies">
		<c:forEach var="loop" items="${cities}" varStatus="current">
			<c:if test="${loop.idString == citySelected}">
				<c:forEach var="loopType" items="${loop.typies}" varStatus="currentType">
					
            			<label>&nbsp;&nbsp;</label>
          			
					<label><a href="<c:url value="/pages/city.jsp">
										<c:param name="city" value="${loop.idString}" />
										<c:param name="cityName" value="${loop.name}" />
										<c:param name="type" value="${loopType.idString}" />
								</c:url>">
           					<c:out value="${loopType.name}" />
           			</a></label>
				</c:forEach>
			</c:if>			
		</c:forEach>
	</div>
	
	<div style="display: none;">
		<table id="tableLocation">
			<c:forEach var="loop2" items="${cities}" varStatus="current">
				<tr>
					<td><c:out value="${loop2.idString}"/></td>
					<td><c:out value="${loop2.longitude}"/></td>
					<td><c:out value="${loop2.latitude}"/></td>
					<td><c:out value="${loop2.mapZoom}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	
	
	<div id="map_canvas" style="width: 100%; height: 500px"></div>
	<script>setMapLocation('cities', 'tableLocation');</script>
	<div id="message"></div>
   <!-- 
   <c:out value="${citiesSize}" />
         
        
        
        	<c:set var="tdclass" value="evenRow_" />
			<c:if test="${current.count % 2 == 0}">
            	<c:set var="tdclass" value="oddRow_" />
          	</c:if>
            
           	        
           	<c:out value="${loop.s}" escapeXml="true"/>  
           	
		 -->
           
					 
		
			
<jsp:include page="elements/footer.jsp"/>