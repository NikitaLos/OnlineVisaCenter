<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 23.04.2018
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Documents</title>
</head>
<body>
<form action="/add_client_documents" method="post" enctype="multipart/form-data">
    <table style="with: 50%">

        <tr>
        <td>Download <c:forEach  items="${document_types}" var="document_type">${document_type.name} </c:forEach></td>
        <td><input name="files" type="file" multiple/>
        </td>
        </tr>

    </table>
    <input type="submit"/>
</form>
</body>
</html>
