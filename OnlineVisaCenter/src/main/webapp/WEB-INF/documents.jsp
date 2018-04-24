<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 20.04.2018
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document Types</title>
</head>
<body>
<jsp:text>Add Document Type</jsp:text>
<br/>
<sf:form action="/employee/add_document_type" modelAttribute="document_type" method="POST">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><sf:input path="name" type="text"/></td>
        </tr>
    </table>
    <input type="submit"/>
    <jsp:text>${duplicate_message}</jsp:text>
</sf:form>

<br/>

<jsp:text>All Countries</jsp:text>
<br/>
<c:forEach items="${document_types}" var="document_type">
    <tr>
        <td>${document_type.name}</td>
        <td>
            <form method="get" action="/employee/delete_document_type/${document_type.id}">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
</c:forEach>
</body>
</html>
