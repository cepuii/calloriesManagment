<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>
<header>
    <a href="mealsServlet"><fmt:message key="app.title"/></a> |
    <a href="usersServlet"><fmt:message key="user.title"/></a> |
    <a href="index.jsp"><fmt:message key="app.home"/></a>
</header>
