<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 22.04.2018
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visa Info</title>
</head>
<body>
<form action="/add_visa_info"  method="POST">
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
        <tr>
            <td>Name</td>
            <td><input name="nameOfClient" type="text" /></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input name="surnameOfClient" type="text"/></td>
        </tr>
        <tr>
            <td>Number of days</td>
            <td><input name="numOfDaysResidence" type="number"/></td>
        </tr>
        <tr>
            <td>Date from</td>
            <td><input name="from" type="date"/></td>
        </tr>
        <tr>
            <td>Date to</td>
            <td><input name="to" type="date" /></td>
        </tr>
    </table>
    <input type="submit"/>
</form>
</body>
</html>
