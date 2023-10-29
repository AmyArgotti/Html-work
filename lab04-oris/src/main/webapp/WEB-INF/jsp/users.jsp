<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Users</title>
</head>
<body>
<h1>From JSP Users</h1>
<div>
  <table>
    <tr>
      <th>ID</th>
      <th>NAME</th>
      <th>SURNAME</th>
    </tr>
    <c:forEach items="${usersForJsp}" var="user">
      <tr>
        <td><c:out value="${user.getId()}" /></td>
        <td><c:out value="${user.getName()}" /></td>
        <td><c:out value="${user.getSurname()}" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>