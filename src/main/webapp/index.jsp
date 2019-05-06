<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="shortcut icon" href="<%=basePath%>images/earth.png" type="image/x-icon" />
<html>
<body>
<h2>Hello World!</h2>
<a href="hello">controller</a>
<script>

    window.location = "showGoods";
</script>
</body>
</html>
