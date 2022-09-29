<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meals</title>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3><fmt:message key="meal.title"/></h3>
<form method="get" action="mealsServlet">
    <input type="hidden" name="action" value="filter">
    <dl>
        <dt>From Date:</dt>
        <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
    </dl>
    <dl>
        <dt>To date:</dt>
        <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
    </dl>
    <dl>
        <dt>From Time:</dt>
        <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
    </dl>
    <dl>
        <dt>To time:</dt>
        <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
    </dl>
    <button type="submit">Filter</button>
</form>
<table style="border: black solid">
    <tr>
        <th>Date and time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <c:forEach items="${requestScope.meals}" var="meal">
        <jsp:useBean id="meal" type="edu.cepuii.caloriesmanagment.to.MealTO"/>
        <tr data-meal-excess="${meal.excess}">
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
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
