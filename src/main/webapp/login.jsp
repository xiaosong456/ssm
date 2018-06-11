<%--
  Created by IntelliJ IDEA.
  User: lxs
  Date: 2018/5/30
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>LOGIN JSP</h4>
<form action="${pageContext.request.contextPath}/shiro/login" method="post">
    uername:<input type="text" name="username" />
    <br><br>
    password:<input type="password" name="password" />
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
