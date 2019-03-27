<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>单价大于1000的商品销售量</title>
	<script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
	<script>
		function exportExcel() {
		    var ids = document.getElementsByName("id").length;
		    var id = "",
				name = "",
				price = "",
				number = "",
				subTotal = "";
		    for (var i=0;i<ids;i++){
                id+=(document.getElementsByName("id")[i].value)+",";
                name += document.getElementsByName("name")[i].value+",";
                price += document.getElementsByName("price")[i].value+",";
                number += document.getElementsByName("number")[i].value+",";
                subTotal += document.getElementsByName("subTotal")[i].value+",";
		    }
            // alert(id);
            // alert(name);
            // alert(price);
            // alert(number);
            // alert(subTotal);
            //用window.location可以在浏览器页面下载，用ajax请求不可以在页面下载
            window.location.href = "exportExcel?id="+id+"&name="+name+"&price="+price+"&number="+number+"&subTotal="+subTotal;

		    /*$.ajax({
                url: "exportExcel",
                type: "POST",
                data: {
                    "id": id,
                    "name": name,
                    "price": price,
                    "number": number,
                    "subTotal": subTotal
                },
                dataType : "json",
                // contentType: "application/json;charset=utf-8",
                success: function(result) {
                    response = result.success;
                    var path = response.substr(2,(response.length-4));
                    alert("导出成功！文件目录:"+path);
                    if (response=="error") {
                        location.href = "error.jsp";
                    } else {
                        location.href = "goodsSaleDetailsByPrice1000";
                    }
                }
            });*/
        }
		
	</script>
</head>
<body>
<form action="" id="goodsSaledetailsForm">
<table style="margin-left: 200px;margin-top: 50px">
<tr>
		<td>
		<input type="text" id="id" name="" value="商品ID" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="name" name="" value="商品名称" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="price" name="" value="单价" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="number" name="" value="数量" style="text-align: center;" disabled="disabled">
		</td>
		<td>
		<input type="text" id="subTotal" name="" value="小计" style="text-align: center;" disabled="disabled">
		</td>
	</tr>
<c:forEach items="${goodsSaleDetails}" var="GS" >
	<tr>
		<td>
		<input  type="text" id="goodsId"  name="id" value="${GS.goods.id}" style="text-align: center;">
		</td>
		<td>
		<input type="text" id="goodsName"  name="name" value="${GS.goods.name}"  style="text-align: center;">
		</td>
		<td>
		<input type="text" id="goodsPrice" name="price" value="${GS.goods.price}"  style="text-align: center;">
		</td>
		<td>
		<input type="text" id="saleDetailNumber" name="number" value="${GS.saleDetail.number}" style="text-align: center;">
		</td>
		<td>
		<input type="text" id="saleDetailSubTotal" name="subTotal" value="${GS.saleDetail.subTotal}" style="text-align: center;">
		</td>
		<td>
		</td>
	</tr>
</c:forEach>
</table>
</form>
<td>
	<input type="submit" id="" name="" value="导出Excel" style="margin-left: 600px;border: 1px solid lightskyblue;text-align: center;width: 80px"onclick="exportExcel()">
<a href="showGoods" style="margin-left: 0px">返回</a>
</td>
</body>
</html>