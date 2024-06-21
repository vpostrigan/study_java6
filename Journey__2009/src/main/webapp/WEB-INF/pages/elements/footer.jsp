<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        
    </div>
        <div id="footer">
			<jsp:useBean id="currentData" class="java.util.Date"/>
			
			<p>Copyright&nbsp;&copy;&nbsp;2008&nbsp;-&nbsp;<fmt:formatDate pattern="yyyy" value="${currentData}"/>&nbsp;Postrigan&nbsp;V.A.</p>
	    </div>
</body>
</html>