<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<br><br>
Welcome:<shiro:principal></shiro:principal>
<br><br>
<shiro:hasRole name="admin">
<a href="admin.jsp">admin Page</a>
</shiro:hasRole>
<br><br>
<shiro:hasRole name="user">
<a href="user.jsp">user Page</a>
</shiro:hasRole>
<br><br>
<a href="shiro/logout">logout</a>
<br><br>
<a href="shiro/testShiroAnnotaion">testShiroAnnotaion</a>
</body>
</html>
