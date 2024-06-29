<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head><title>Context binding JSP</title></head>
<body>
<h2>Here is the bound ContextObject</h2>
<%--
<jsp:useBean id="contextObj" class="chap12.ContextObject" />
<jsp:useBean id="date" class="java.util.Date" /> 
<c:set var="chap12.ContextObject" value="${contextObj}" scope="application" />
--%>
<c:set target="${applicationScope[\"chap12.ContextObject\"].map}" value="${date}" property="${pageContext.request.remoteAddr}"/>
<c:out value="${\"chap12.ContextObject\".values}" escapeXml="false" />
</body>
</html>