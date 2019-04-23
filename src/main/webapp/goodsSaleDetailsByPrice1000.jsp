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
		    var
                saleNo = "",
				id = "",
				name = "",
				price = "",
				number = "",
				subTotal = "";
		    for (var i=0;i<ids;i++){
                saleNo+=(document.getElementsByName("saleNo")[i].value)+",";
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
            window.location.href = "exportExcel?saleNo="+saleNo+"&id="+id+"&name="+name+"&price="+price+"&number="+number+"&subTotal="+subTotal;

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
<form action="" id="goodsSaledetailsForm" style="margin-top: 50px">
<div>
	<td>
		<input type="submit" id="" name="" value="导出Excel" style="margin-left: 350px;border: 1px solid lightskyblue;text-align: center;width: 80px"onclick="exportExcel()">
		<a href="showGoods" style="margin-left: 0px">返回</a>
	</td>
<table style="margin-left:100px;">
<tr>
		<td>
		<input type="text" id="saleNo" name="" value="商品销售ID" style="text-align: center;width: 100px" disabled="disabled">
		</td>
		<td>
		<input type="text" id="id" name="" value="商品ID" style="text-align: center;width: 100px" disabled="disabled">
		</td>
		<td>
		<input type="text" id="name" name="" value="商品名称" style="text-align: center;width: 100px" disabled="disabled">
		</td>
		<td>
		<input type="text" id="price" name="" value="单价" style="text-align: center;width: 100px" disabled="disabled">
		</td>
		<td>
		<input type="text" id="number" name="" value="数量" style="text-align: center;width: 100px" disabled="disabled">
		</td>
		<td>
		<input type="text" id="subTotal" name="" value="小计" style="text-align: center;width: 100px" disabled="disabled">
		</td>
			<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
			<div id="main" style="width: 500px;height:400px;float: right"></div>
	</tr>
<c:forEach items="${goodsSaleDetails}" var="GS" >
	<tr>
		<td>
		<input  type="text" id="goodsSaleNo"  name="saleNo" value="${GS.saleDetail.saleNo}" style="text-align: center;width: 100px">
		</td>
		<td>
		<input  type="text" id="goodsId"  name="id" value="${GS.goods.id}" style="text-align: center;width: 100px">
		</td>
		<td>
		<input type="text" id="goodsName"  name="name" value="${GS.goods.name}"  style="text-align: center;width: 100px">
		</td>
		<td>
		<input type="text" id="goodsPrice" name="price" value="${GS.goods.price}"  style="text-align: center;width: 100px">
		</td>
		<td>
		<input type="text" id="saleDetailNumber" name="number" value="${GS.saleDetail.number}" style="text-align: center;width: 100px">
		</td>
		<td>
		<input type="text" id="saleDetailSubTotal" name="subTotal" value="${GS.saleDetail.subTotal}" style="text-align: center;width: 100px">
		</td>
		<td>
		</td>
	</tr>
</c:forEach>
</table>
</div>
</form>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    var data = genData();
    option = {
        title : {
            text: '商品销售数量统计',
            subtext: '单价大于1000商品销售数量统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: data.legendData,

            selected: data.selected
        },
        series : [
            {
                name: '商品销量总额统计',
                type: 'pie',
                radius : '55%',
                center: ['40%', '50%'],
                data: data.seriesData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    //获取商品名称
    function getGoodsNameList() {
        var nameList = [];
        //ajax为异步请求，设置ajax为同步请求：async = false
        $.ajaxSettings.async = false;
        $.post("findGoodsSaledetailsChart",{},function (result) {
            for (var i=0;i<result.length;i++){
                nameList.push(result[i]['name']);
            }
        });
        return nameList;
    }
    //获取商品订单数
    function getGoodsCount() {
        var count = [];
        //ajax为异步请求，设置ajax为同步请求：async = false
        $.ajaxSettings.async = false;
        $.post("findGoodsSaledetailsChart",{},function (result) {
            for (var i=0;i<result.length;i++){
                count.push(result[i]['count']);
            }
        });
        return count;
    }
    //商品销售数量总和
    function getSumNumber() {
        var sumNumber = [];
        //ajax为异步请求，设置ajax为同步请求：async = false
        $.ajaxSettings.async = false;
        $.post("findGoodsSaledetailsChart",{},function (result) {
            for (var i=0;i<result.length;i++){
                sumNumber.push(result[i]['sumNumber']);
            }
        });
        return sumNumber;
    }
    //获取商品订单小计之和
    function getSumSubTotal() {
        var sumSubTotal = [];
        //ajax为异步请求，设置ajax为同步请求：async = false
        $.ajaxSettings.async = false;
        $.post("findGoodsSaledetailsChart",{},function (result) {
            for (var i=0;i<result.length;i++){
                sumSubTotal.push(result[i]['sumSubTotal']);
            }
        });
        return sumSubTotal;
    }

    function genData() {

        var goodsNameListArr = [];
        var countArr = [];
        var sumNumberArr = [];
        var sumSubTotalArr = [];
        goodsNameListArr = getGoodsNameList();
        countArr = getGoodsCount();
        sumNumberArr = getSumNumber();
        sumSubTotalArr = getSumSubTotal();
        /*alert(goodsNameList);
        alert(count);
        alert(sumNumber);
        alert(sumSubTotal);*/
        //饼图数据
        var legendData = [];
        var seriesData = [];
        var selected = {};

        var count = 0;
        var sumNumber = '';
        var sumSubTotal = '';
        for (var i = 0; i < goodsNameListArr.length; i++) {
            name = goodsNameListArr[i];
            count = countArr[i];
            sumNumber = sumNumberArr[i];
            sumSubTotal = sumSubTotalArr[i];
            legendData.push(name);
            var pushName = "商品名称:"+name+"---商品订单数:"+count+"---商品销售数量:"+sumNumber+"---商品销售总额:"+sumSubTotal
            seriesData.push({
                name: name,
                value: sumNumber
            });
            selected[name] = i < 6;
        }
        return {
            legendData: legendData,
            seriesData: seriesData,
            selected: selected
        };

        /*var nameList = [
            '赵', '钱'
        ];
        function makeWord(max, min) {
            var nameLen = Math.ceil(Math.random() * max + min);
            var name = [];
            for (var i = 0; i < nameLen; i++) {
                name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
            }
            return name.join('');
        }*/
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>