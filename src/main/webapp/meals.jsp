<%--
  Created by IntelliJ IDEA.
  User: cepui
  Date: 16.07.2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meals</title>
</head>
<body>
<table style="border: black solid">
    <tr>
        <th>Date and time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.Collection"/>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                               var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><c:if test="${meal.excess eq true}"> Превышен </c:if>
                <c:if test="${meal.excess eq false}"> Норма </c:if>
            </td>
            <td><a href="mealsServlet?action=update&id=${meal.id}">Update</a></td>
            <td><a href="mealsServlet?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="mealsServlet?action=add">Add meal</a></p>

</body>
</html>
