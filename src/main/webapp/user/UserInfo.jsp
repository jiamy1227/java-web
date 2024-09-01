<%@ page import="com.jiamy.servlet.jsp.User" %><%--
  Created by IntelliJ IDEA.
  User: 10494
  Date: 2024/9/1
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% User user = (User) request.getAttribute("user");%>
UserName: <% out.print(user.getName()); %>
UserAge: <% out.print(user.getAge()); %>
</body>
</html>
