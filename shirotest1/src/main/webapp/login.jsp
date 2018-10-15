<%--
  Created by IntelliJ IDEA.
  User: 黄杰
  Date: 2018/10/15
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>login</h1>
<form action="shiro/login" method="post">
    userName: <input type="text" name="username"><br><br>
    password: <input type="password" name="password">
    <input type="submit" name="submit">

</form>
</body>
</html>
