<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 22.04.2018
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Visa</title>
</head>
<body>
<form action="/add_visa"  method="POST">
    <table style="with: 50%">
        <tr>
            <td>Choose Visa</td>
            <td>
                <select name="visaId">
                    <c:forEach  items="${country.availableVisas}" var="visa">
                        <option value="${visa.id}">${visa.type}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit"/>
</form>
</body>
</html>
