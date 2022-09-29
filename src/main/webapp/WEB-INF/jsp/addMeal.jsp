
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
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <form method="post" action="mealsServlet">
        <jsp:useBean id="meal" scope="request" class="edu.cepuii.caloriesmanagment.model.Meal"/>
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="date" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required>
            </dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
<%--</head>--%>
<%--<body>--%>
<%--<div style="align-content: center">--%>
<%--    <form action="meals" method="post">--%>
<%--        <input type="hidden" name="id" value="${meal.id}">--%>
<%--        <label for="date">Date and time: </label>--%>
<%--        <input type="datetime-local" id="date" name="date" value="${meal.dateTime}"><br>--%>
<%--        <label for="description">Description: </label>--%>
<%--        <input type="text" id="description" name="description" value="${meal.description}"><br>--%>
<%--        <label for="calories">Calories</label>--%>
<%--        <input type="number" id="calories" name="calories" value="${meal.calories}">--%>
<%--        <input type="submit" name="Add/update">--%>
<%--    </form>--%>
<%--</div>--%>
</body>
</html>
