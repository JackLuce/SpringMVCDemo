<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>单价大于1000的商品销售量</title>
</head>
<body>
<form action="">
<table style="margin-left: 200px;margin-top: 50px">
<tr>
		<td>
		<input type="text" id="id" name="id" value="商品ID" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="name" name="name" value="商品名称" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="price" name="price" value="单价" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="number" name="number" value="数量" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="subTotal" name="subTotal" value="小计" style="text-align: center;" disabled="disabled">
		</td>
	</tr>
<c:forEach items="${goodsSaleDetails}" var="GS" >
	<tr>
		<td>
		<input type="text" id="id"  name="id" name="" value="${GS.goods.id}" style="text-align: center;">
		</td>
		<td>
		<input type="text" id="name"  name="name" value="${GS.goods.name}"  style="text-align: center;">
		</td>
		<td>
		<input type="text" id="name" name="price" value="${GS.goods.price}"  style="text-align: center;">
		</td>
		<td>
		<input type="text" id="number" name="number" value="${GS.saleDetail.number}" style="text-align: center;">
		</td>
		<td>
		<input type="text" id="subTotal" name="subTotal" value="${GS.saleDetail.subTotal}" style="text-align: center;">
		</td>
	</tr>
</c:forEach>
</table>
</form>
<a href="showGoods" style="margin-left: 600px">返回</a>
</body>
</html>