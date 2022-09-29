<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>
<br>
<section>
    <form method="post" action="usersServlet">
        <fmt:message key="app.login"/>: <select name="userId">
        <option value="100000">User</option>
        <option value="100001">Admin</option>
    </select>
        <button type="submit">Select</button>
    </form>
</section>
<jsp:include page="../../fragments/footer.jsp"/>
</body>
</html>