<%--
  Created by IntelliJ IDEA.
  User: lxs
  Date: 2018/5/30
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>LIST JSP</h4>

Welcome：<shiro:principal  property="username"></shiro:principal>


<br>
<a href="${pageContext.request.contextPath}/shiro/testShiroAnnotation">Test ShiroAnnotation</a>
<br>
<a href="${pageContext.request.contextPath}/shiro/admin">Admin Permission</a>




<br>
<a href="shiro/logout">Logout</a>
</body>
</html>
