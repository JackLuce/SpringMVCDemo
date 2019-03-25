<%--
  Created by IntelliJ IDEA.
  User: luominjie
  Date: 2019/3/23
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1 style="color: green;margin-left: 300px;">
success!添加了<%=num%>条数据！
</h1>
<br>
<a href="showGoods" style="margin-left: 600px">返回</a>
</body>
</html>
