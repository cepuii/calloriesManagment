<jsp:useBean id="meal" scope="request" type="edu.cepuii.calloriesmanagment.model.Meal"/>
<%--
  Created by IntelliJ IDEA.
  User: cepui
  Date: 18.07.2022
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add meal</title>
</head>
<body>
<div style="align-content: center">
    <form action="meals" method="post">
        <input type="hidden" name="id" value="${meal.id}">
        <label for="date">Date and time: </label>
        <input type="datetime-local" id="date" name="date" value="${meal.dateTime}"><br>
        <label for="description">Description: </label>
        <input type="text" id="description" name="description" value="${meal.description}"><br>
        <label for="calories">Calories</label>
        <input type="number" id="calories" name="calories" value="${meal.calories}">
        <input type="submit" name="Add/update">
    </form>
</div>
</body>
</html>
