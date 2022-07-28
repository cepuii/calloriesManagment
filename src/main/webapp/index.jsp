<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calories management</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<hr/>
<form method="post" action="usersServlet">
    <b style="font-size: large">Meals of&nbsp;</b>
    <select name="userId">
        <option value="1">User</option>
        <option value="2">Admin</option>
    </select>
    <button type="submit">Select</button>
</form>
</body>
</html>