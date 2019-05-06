<%--
  Created by IntelliJ IDEA.
  User: luominjie
  Date: 2019/3/25
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="shortcut icon" href="<%=basePath%>images/earth.png" type="image/x-icon" />

<html>
<head>
    <title>error</title>
</head>
<body>
<h1 style="color: red">
    error!
</h1>
<br>
<a href="showGoods">返回</a>
</body>
</html>
