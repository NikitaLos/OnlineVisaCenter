<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 14.04.2018
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Log In</title>
</head>
<body>
<form action="/login" method="post">
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input name="login" type="text"  /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input name="password" type="password"/></td>
        </tr>
    </table>
    <input type="submit"/>
    <jsp:text>${not_register_message}</jsp:text>
</form>
</body>
</html>
