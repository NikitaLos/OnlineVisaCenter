<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 20.04.2018
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Visas</title>
</head>
<body>
<jsp:text>Add Visa</jsp:text>
<br/>
<sf:form action="/employee/add_visa" modelAttribute="visa"  method="POST">
    <table style="with: 50%">
        <tr>
            <td>type</td>
            <td><sf:input path="type" type="text"/></td>
        </tr>
        <tr>
            <td>price</td>
            <td><sf:input path="price" type="number"/></td>
        </tr>
            <tr>
            <td>country</td>
            <td>
                <select name="countryId">
                    <c:forEach  items="${countries}" var="country">
                        <option value="${country.id}">${country.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>document types</td>
            <td>
                <select name="documentTypes"  multiple="multiple">
                    <c:forEach  items="${document_types}" var="document_type">
                        <option value="${document_type.id}">${document_type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit"/>
    <jsp:text>${duplicate_message}</jsp:text>
</sf:form>
<br/>
<jsp:text>All Visas</jsp:text>
<br/>
<c:forEach items="${visas}" var="visa">
    <tr>
        <td>${visa.country.name}</td>
        <td>${visa.type}</td>
        <td>${visa.price}</td>
        <td>
            <form method="get" action="/employee/delete_visa/${visa.id}">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
</c:forEach>
</body>
</html>
