<%--
  Created by IntelliJ IDEA.
  User: losni
  Date: 19.04.2018
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All Users</title>
</head>
<body>

<jsp:text>Add Employee</jsp:text>
<br/>
<form action="/admin/add_employee" method="POST">
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>
                <select name="role">
                    <option>ADMIN</option>
                    <option>EMPLOYEE</option>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit"/>
    <jsp:text>${duplicate_message}</jsp:text>
</form>

<br/>

<jsp:text>All Employees</jsp:text>
<br/>

<c:forEach items="${all_employees}" var="employee">
    <tr>
        <td>${employee.login}</td>
        <td>${employee.email}</td>
        <td>${employee.role}</td>
        <td>
            <form method="get" action="/admin/delete_user/${employee.id}">
                <button type="submit" >Delete</button>
            </form>
        </td>
    </tr>
</c:forEach>
</body>
</html>
