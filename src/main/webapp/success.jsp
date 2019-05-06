<%--
  Created by IntelliJ IDEA.
  User: luominjie
  Date: 2019/3/23
  Time: 17:13
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
    <title>success</title>
    <script>
        <%
        String num =request.getParameter("num");
        %>
    </script>
</head>
<body>
<h1 style="color:#F4A460;margin-left: 50px;margin-top:200px;text-align: center">
    success!添加了<a style="color: greenyellow"><%=num%></a>条商品销售数据！
</h1>
<br>
<a href="showGoods" style="margin-left: 600px">返回</a>
</body>
</html>
