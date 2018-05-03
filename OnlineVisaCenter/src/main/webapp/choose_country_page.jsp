<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 22.04.2018
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose country and Visa type</title>
</head>
<body>
<form action="/add_country"  method="POST">
    <table style="with: 50%">
        <tr>
            <td>Chose Country</td>
            <td>
                <select name="countryId">
                    <c:forEach  items="${countries}" var="country">
                    <option value="${country.id}">${country.name}</option>
                    </c:forEach>
                </select >
            </td>
        </tr>
    </table>
    <input type="submit"/>
</form>
</body>
</html>
