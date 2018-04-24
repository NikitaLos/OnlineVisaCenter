<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 19.04.2018
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
</head>
<body>
<jsp:text>Add Country</jsp:text>
<br/>
<sf:form action="/employee/add_country" modelAttribute="country" method="POST">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><sf:input path="name" type="text" /></td>
        </tr>
    </table>
    <input type="submit"/>
    <jsp:text>${duplicate_message}</jsp:text>
</sf:form>

<br/>

<jsp:text>All Countries</jsp:text>
<br/>
<c:forEach items="${countries}" var="country">
    <tr>
        <td>${country.name}</td>
        <td>
            <form method="get" action="/employee/delete_country/${country.id}">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
</c:forEach>
</body>
</html>
