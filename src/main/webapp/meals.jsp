<%--
  Created by IntelliJ IDEA.
  User: cepui
  Date: 16.07.2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table>
    <tr>
        <th>Date and time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><c:if test="${meal.excess eq true}"> Превышен </c:if>
                <c:if test="${meal.excess eq false}"> Норма </c:if>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
