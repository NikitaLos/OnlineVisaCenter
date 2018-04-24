<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 22.04.2018
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Info</title>
</head>
<body>
<form action="/add_client_info"  method="POST">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><input name="name" type="text" /></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input name="surname" type="text"/></td>
        </tr>
        <tr>
            <td>Sex</td>
            <td><input name="sex" type="radio" value="M"/>M</td>
            <td><input name="sex" type="radio" value="W"/>W</td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input name="phoneNumber" type="text"/></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input name="dateBirth" type="date" /></td>
        </tr>
        <tr>
            <td>Aim of Visit</td>
            <td>
                <select name="aim">
                    <c:forEach  items="${aims}" var="aim">
                        <option value="${aim}">${aim}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Photo</td>
            <td><input name="photoPathOnServer" type="text"/></td>
        </tr>
        <tr>
            <td>Passport Number</td>
            <td><input name="number" type="text"/></td>
        </tr>
        <tr>
            <td>Country Of Residence</td>
            <td><input name="countryOfResidence" type="text"/></td>
        </tr>
        <tr>
            <td>Date of Receiving</td>
            <td><input name="dateReceiving" type="date" /></td>
        </tr>
        <tr>
            <td>Date of Ending</td>
            <td><input name="dateEnding" type="date"/></td>
        </tr>
    </table>
    <input type="submit"/>
</form>
</body>
</html>
