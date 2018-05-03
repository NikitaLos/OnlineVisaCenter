<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 25.04.2018
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<form action="/pay" method="post">
    <table style="with: 50%">
        <tr>
            <td>Pay ${price}</td>
        </tr>
    </table>
    <input type="hidden" name="price" value="${price}">
    <input type="submit"/>
</form>
</body>
</html>
