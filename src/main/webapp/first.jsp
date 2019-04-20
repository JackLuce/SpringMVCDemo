<%--
  Created by IntelliJ IDEA.
  User: luominjie
  Date: 2019/3/22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <%--<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>--%>
    <%--<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script><br/>--%>
    <%--<script type="text/javascript" src="js/jquery-3.0.0.js"></script><br/>--%>

    <script>
        var iCnt = 0;
        /**  新增一行js function  */
        function addTable(obj) {
            //获取后台返回页面下拉框的值
            var goodsName = document.getElementById('selectGoodsName');
            var price = document.getElementById('SelectPrice');
            //后台数据库返回值select值的个数
            var goodsNameLength = goodsName.options.length;
            var priceLength = price.options.length;
            //select 下拉框中的options 值
            var goodsNameOptions = goodsName.options;
            var priceOptions = price.options;
            //table新增一行  拼接  +"selected="+'"selected"'
            var trTable = "<tr><td><input type="+'"checkbox"'+" id="+'"id"'+" name="+'"id"'+" style="+'"text-align: center;border: 1px solid lightskyblue;width: 80px"'+"></td><td><select id="+'"selectGoodsName"'+" name="+'"selectGoodsName"'+" class="+'"GoodsName"'+"selected="+'"selected"'+" onchange="+'"toGoodsPrice(this)"'+" style="+'"text-align: center;border: 1px solid lightskyblue;width: 80px"'+"></select></td><td><select disabled id="+'"SelectPrice"'+" name="+'"SelectPrice"'+" class="+'"SelectPrice"'+" style="+'"text-align: center;border: 1px solid lightskyblue;width: 80px"'+">${goods.price}</select></td><td><input type="+'"text"'+" id="+'"goodsNumber"'+" name="+'"goodsNumber"'+" style="+'"text-align: center;border: 1px solid lightskyblue;width: 80px"'+"onchange="+'"myMathFunction(this)"'+"></td><td><input type="+'"text"'+" id="+'"goodsSubTotal"'+" name="+'"goodsSubTotal"'+"disabled style="+'"text-align: center;border: 1px solid lightskyblue;width: 120px;background:#F4A460"'+"></td><td></td></tr>";
            var  xx = $(trTable);
            //table 结尾新增一行
            $("#myTable").append(xx);
            iCnt++;
            //根据class属性获取新增一行中的select
            var GoodsName=$(".GoodsName") ;
            var GoodsPrice=$(".SelectPrice") ;
            //新增一行下拉框初始值options
            var nameOpt='<option value="-1" selected="selected">--请选择--</option>';
            var priceOpt='<option value="-1" selected="selected">-----</option>';
            //根据原有select options值个数 循环遍历对新增一行的select下拉框进行赋值
            /* GoodsName 下拉框  */
            for(var i = 0; i < goodsNameLength; i++){
                //如果原有select没有值则初始化新增select
                if(goodsNameLength==0){
                    GoodsName.html(nameOpt);
                    return ;
                }
                //each 为options赋值
                $.each(goodsNameOptions,function(i,item){
                    var tmp = goodsName.options[i].value;
                    nameOpt+='<option name="GoodsName" value="'+tmp+'" >'+tmp+'</option>';
                    //根据class属性对select进行赋值，存在问题：每次都会对相同class属性的select进行一行赋值，options重复
                    // jQuery(".GoodsName").append("<option value="+tmp+">"+tmp+"</option>");
                });
                //将options添加到select中
                // debugger;
                /**
                 * 选中值后再添加一行数据不清空
                 */
                /* -------start---------*/
                // var GoodsNameSelected = document.getElementById('selectGoodsName');//selectGoodsName select标签的id
                // alert(GoodsNameSelected.value.trim())
                var trNum = $("#myTable").find("tr").length;
                var SGoodsName = '';
                var SPrice = '';
                //修改商品名称的 class属性值  因为商品名称options是通过.class属性值添加的
                // GoodsName.html(nameOpt);会覆盖选中的值
                $(".GoodsName").attr("class","blue");
                $(".SelectPrice").attr("class","blue");
                for(var i=1;i<trNum;i++){
                    SGoodsName = $("table#myTable").find("tr:eq("+i+")").find("select[name='selectGoodsName']").val();
                    SPrice = $("table#myTable").find("tr:eq("+i+")").find("select[name='SelectPrice']").val();
                    // alert("第"+i+"行中字段值：商品名称--->"+SGoodsName+",商品价格--->"+SPrice);
                    if(SGoodsName=="-1"||SGoodsName==undefined){
                        // alert("第"+i+"行，unselected!");
                    }else {
                        // alert("第"+i+"行，selected："+SGoodsName+","+SPrice);
                        // debugger;
                        $("table#myTable").find("tr:eq("+i+")").find("select[id='selectGoodsName']").val(SGoodsName);
                        $("table#myTable").find("tr:eq("+i+")").find("select[id='SelectPrice']").val(SPrice);
                    }
                }
                /* -------end---------*/
                GoodsName.html(nameOpt);
                // debugger;
                /* GoodsPrice  下拉框  */
                for(var i = 0; i < priceLength; i++){
                    //如果原有select没有值则初始化新增select
                    if(priceLength==0){
                        GoodsPrice.html(priceOpt);
                        return ;
                    }
                    //each 为options赋值
                    $.each(priceOptions,function(i,item){
                        var tmp = price.options[i].value;
                        priceOpt+='<option name="SelectPrice" value="'+tmp+'" >'+tmp+'</option>';
                    });
                    //将options添加到select中
                    GoodsPrice.html(priceOpt);
                    return ;
                }
                //return 之后的代码不执行
                return ;
                alert("return后不执行------------");
                //分别是获取tr、td的长度
                // var trNum = $("#myTable").find("tr").length;
                // var tdNum = $("#myTable").find("td").length;
            }
            //ajax请求数据格式
            /*$.ajax({
                url: "selectGoods",
                type: "POST",
                data:"",
                dataType : "json",
                success: function(result) {
                    var dataJson = JSON.stringify(result);
                    var goods = result[goods];
                    response = result.goods;
                    alert(dataJson);
                    alert(response);
                    alert("success");
                }
                });*/
        }
        /**
         * 根据name回显相对应的价格
         *
         */
        function toGoodsPrice(obj) {
            //当前对象的值
            var goodsName = obj.value;
            $.ajax({
                url: "selectPriceByName",
                type: "POST",
                data: {
                    "name": goodsName
                },
                dataType : "json",
                success: function(result) {  //res是server端响应
                    /*                 response = result.success; */
                    //转json
                    // var dataJson = JSON.stringify(result);
                    response = result.goods;
                    //截取price
                    var length =response.length;
                    //数据库价格
                    var price = response.substr(2,(length-4));
                    //当前显示价格
                    var goodsPrice=document.getElementById("goodsPrice").value;
                    if (response!="") {
                        goodsPrice = price;
                        var tr ;
                        // alert(iCnt+"行");  添加了几行
                        // var SelectPrice = document.getElementById('SelectPrice');
                        // alert("SelectPrice个数-----"+SelectPrice.length);
                        //所有的select值都一样
                        //  $("select#SelectPrice").val(goodsPrice);
                        /**
                         * 当前商品被选中时给对应下拉框进行赋值
                         * */
                        $("option[name='GoodsName']:selected").each(function () {//被选中时each操作
                            // alert("当前商品名称--->"+this.value+",正确价格--->"+price);
                            trNum = $(this).parents("tr").index();
                            tdNum = $(this).parents("td").index();
                            // alert("第"+trNum+"行，第"+tdNum+"列");
                            // alert("第"+trNum+"行，第"+(++tdNum)+"列");
                            //当前行的下一列
                            // var cc= $("table#myTable").find("tr:eq("+trNum+")").find("tr:eq("+(++tdNum)+")").val();
                            /**
                             * 为当前行的对应下拉框赋值
                             * */
                            //获取上上级tr 的demo
                             tr= obj.parentNode.parentNode;
                            //通过find()方法获取tr行中字段值
                            // var ss=$(tr).find("select[name='SelectPrice']").val();
                            // alert(ss+"--->tr行中字段值");
                            $(tr).find("select[name='SelectPrice']").val(goodsPrice);
                        });
                        //获取商品数量
                        var goodsNumber=$(tr).find("input[name='goodsNumber']").val();
                        //如果商品数量大于0，调用小计方法
                        if(goodsNumber>0){
                            myMathFunction(obj);
                        }

                    } else {
                        location.href = "error.jsp";
                    }
                }
            });
        }
        /**
         删除所选行
         */
        function deleteSelectedRow(rowID) {
            //tr行数
            // var trNum = $("#myTable").find("tr").length;
            if ($("input[name='id']:checked").length>0){
                var con = confirm("确定删除所选行吗？");
                var n = 0;
                // alert(con);
                if (con == false) {
                    //取消删除，取消checkbox选中
                    $("input[name='id']:checked").each(function() { // 遍历选中的checkbox
                        n = $(this).parents("tr").index();
                        var boxes = document.getElementsByName("id");
                        for (var i=0;i<boxes.length;i++){
                            boxes[i].checked = false;
                        }
                    });
                }else {
                    $("input[name='id']:checked").each(function() { // 遍历选中的checkbox
                        n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
                        /**
                         * 原始数据不可删除
                         */
                        if (n == 1) {
                            var boxes = document.getElementsByName("id");
                            for (var i = 0; i < boxes.length; i++) {
                                boxes[i].checked = false;
                            }
                            //商品数量置空
                            $("table#myTable").find("tr:eq("+n+")").find("input[id='goodsNumber']").val('');
                            //小计置空
                            $("table#myTable").find("tr:eq("+n+")").find("input[id='goodsSubTotal']").val('');

                            alert("首行原始数据不可删，已重置！");
                        }else {
                            $("table#myTable").find("tr:eq("+n+")").remove();
                        }
                    });

                    //调用合计方法 重新计算合计
                    TotalPrice();

                    $("#" + rowID).remove();
                }
            }else {
                alert("请选择要删除的数据！");
            }
        }
        /**
         * refresh 刷新
         */
        function refresh(obj) {
            //获取上上级tr 的demo
            var tr = obj.parentNode.parentNode;
            $("input[name='id']").each(function () { // 遍历选中的checkbox
                var n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
                if(n==1){
                    //原始数据不清空
                }else {
                    //商品名称置空、价格置空  class已被修改为'blue' 见71、72行
                    $(tr).find("select[class='blue']").val("-1");
                }
                //商品数量置空
                $(tr).find("input[id='goodsNumber']").val("");
                //小计置空
                $(tr).find("input[id='goodsSubTotal']").val('');
                //合计置空
                $(tr).find("input[id='SubTotal']").val('');
                /**
                 * 取消checkbox选中
                 * */
                var boxes = document.getElementsByName("id");
                for (var i = 0; i < boxes.length; i++) {
                    boxes[i].checked = false;
                }
            });

        }
        /**
         * 合计方法
         */
        function TotalPrice() {
            //合计
            var SubTotalNum = 0;
            //获取合计
            var SubTotal=document.getElementById("SubTotal");
            $("input[name='goodsSubTotal']").each(function () {
                //获取商品小计一列所有数据
                var goodsSubTotal=$(this).parents("tr").find("#goodsSubTotal").val();
                // alert(goodsSubTotal);
                //计算合计 合计=小计之和   前端加法运算 num*1 否则就是字符串拼接
                SubTotalNum = (goodsSubTotal)*1+(SubTotalNum)*1;
            });
            // alert(SubTotalNum+"---------合计");
            //合计赋值
            SubTotal.value=	SubTotalNum;
        }
        /**
         *选择数量时进行小计以及合计
         */
        function myMathFunction(obj){
            //获取上上级tr 的demo
            var tr = obj.parentNode.parentNode;
            //获取商品名称
            var GoodsName=$(tr).find("select[name='GoodsName']").val();
            //获取商品价格
            var goodsPrice=$(tr).find("select[name='SelectPrice']").val();
            //获取商品数量
            // var goodsNumber=document.getElementById("goodsNumber").value;根据id获取
            var goodsNumber=$(tr).find("input[name='goodsNumber']").val();
            //合计
            var SubTotal ;

            var reg =/^\+?[1-9][0-9]*$/;
            if(!reg.test(goodsNumber)){
                alert("请输入大于0的整数！");
                $(tr).find("input[name='goodsNumber']").val('');
                $(tr).find("input[name='goodsSubTotal']").val('');
            }else {
                /**
                 * 如果GoodsName，goodsPrice为-1提示选择商品不计算小计
                 */
                if(GoodsName=="-1"||goodsPrice=="-1"){
                    alert("请先选择商品！");
                    $(tr).find("input[name='goodsNumber']").val('');//置空商品数量
                }else {
                        //商品小计等于数量乘以价格
                        SubTotal = (goodsNumber)*(goodsPrice);
                        $(tr).find("input[name='goodsSubTotal']").val(SubTotal);
                    // var goodsSubTotal=$(tr).find("input[name='goodsSubTotal']").val();
                    // alert("价格："+goodsPrice+"数量"+goodsNumber+"小计"+goodsSubTotal);
                    // var goodsSubTotal=document.getElementById("goodsSubTotal");根据id获取
                    //调用合计方法
                    TotalPrice();
                }
            }
        }
        /**
         * 保存多条数据
         */
        function save() {
            //获取checkbox选中的个数
            var  checkboxNum =  $("input[type='checkbox']:checked").length;
            if (checkboxNum ==0){
                alert("请勾选需要添加的商品数据！");
            } else {
                var name = '';
                var price = '';
                var goodsNumber = '';
                var goodsSubTotal = '';
                $("input[name='id']:checked").each(function() { // 遍历选中的checkbox
                    name+=$(this).parents("tr").find("option[name='GoodsName']:selected").val() + ',';
                    price+=$(this).parents("tr").find("select[name='SelectPrice']").val() + ',';
                    goodsNumber+=$(this).parents("tr").find("#goodsNumber").val() + ',';
                    goodsSubTotal+=$(this).parents("tr").find("#goodsSubTotal").val() + ',';
                    if($(this).parents("tr").find("#goodsNumber").val()==""||$(this).parents("tr").find("#goodsNumber").val()==null){
                        alert("请选择商品数量！！！");
                        return false;
                        //return false终止循环
                        //return true,跳出循环，进入下一次循环，跟函数返回值无关
                    }
                });
                /*alert(name);
               alert(price);
               alert(goodsNumber);
               alert(goodsSubTotal);*/
                $.ajax({
                    url: "insertGoodsList",
                    type: "POST",
                    data: {
                        "name": name,
                        "price": price,
                        "number": goodsNumber,
                        "subTotal": goodsSubTotal
                    },
                    dataType : "json",
                    // contentType: "application/json;charset=utf-8",
                    success: function(result) {
                        response = result.success;
                        var num = response.substr(2,(response.length-4));
                        // alert(response.substr(2,(response.length-4)));
                        if (response=="error") {
                            location.href = "error.jsp";
                        } else {
                            location.href = "success.jsp?num="+num;
                        }
                    }
                });
            }
        }
        /**
         * 单价>1000的商品销量
         */
        function count() {
            window.location = "goodsSaleDetailsByPrice1000";
        }
        /**
         导出饼图
         */
        function exportChart() {
            window.location.href = "findGoodsSaledetailsChart";
        }
    </script>
</head>
<body>
<p style="margin-left: 550px;margin-top: 30px;color: red;font-size: 25px">
首页！
</p>
<div style="margin-left: 373px;margin-top: 20px;">
<tr >
    <td>
        <input id="addTable" type="button" value="增加一行" onclick="addTable(this)" style="width: 80px"/>
    </td>
    <td>
        <input id="deleteTable"  type="button" value="删除选中的行" onclick="deleteSelectedRow(this)" style="width: 100px"/>
    </td>
    <td>
        <a style="margin-left: 65px">合计：</a><input id="SubTotal" style="background:#F4A460" type="text" disabled>
    </td>
</tr>
</div>
<form action="GoodsAddServlet" style=" text-align:center;margin-top: 10px">

    <table id="myTable" style="border: 1px solid lightskyblue;margin-left:373px;text-align: center">
        <tr>
            <td>
                <input type="text"  value="id" style="text-align: center;width: 80px" disabled="disabled">
            </td>
            <td>
                <input type="text" value="商品名称" style="text-align: center;width: 80px" disabled="disabled">
            </td>
            <td>
                <input type="text" value="单价" style="text-align: center;width: 80px" disabled="disabled">
            </td>
            <td>
                <input type="text" value="数量" style="text-align: center;width: 80px" disabled="disabled">
            </td>
            <td>
                <input type="text" value="小计" style="text-align: center;width: 120px" disabled="disabled">
            </td>
        </tr>
        <tr>
            <td>
                <input type="checkbox" id="id" name="id" value="${goods.id}"  style="border: 1px solid lightskyblue;text-align: center;width: 66px">
            </td>
            <td>
                <select name="selectGoodsName" id = "selectGoodsName" class="notSelectGoodsName"  onchange="toGoodsPrice(this)" style="border: 1px solid lightskyblue;text-align: center;width: 82px">
                    <c:forEach items="${listGoods}" var="goods">
                        <option name="GoodsName" value="${goods.name}">${goods.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select disabled name="SelectPrice" id= "SelectPrice" class="notSelectPrice" style="border: 1px solid lightskyblue;text-align: center;width: 82px">
                    <c:forEach items="${listGoods}" var="goods">
                        <option id="goodsPrice" name = "goodsPrice">${goods.price}</option>
                    </c:forEach>
                </select>
            </td>

            <td>
                <input type="text" id="goodsNumber" name="goodsNumber"  value="" style="border: 1px solid lightskyblue;text-align: center;width: 80px" onchange="myMathFunction(this)"><%--background: #1E9FFF--%>
            </td>
            <td>
                <input type="text" id="goodsSubTotal" name="goodsSubTotal" disabled value="" style="border: 1px solid lightskyblue;text-align: center;width: 120px;background: #F4A460">
            </td>

        </tr>
    </table>
</form>
<div style="margin-left: 373px;">
<tr>
    <td>
        <input type="button"  name="" value="刷新" style="margin-left: 100px;border: 1px solid lightskyblue;text-align: center;width: 80px"onclick="refresh(this)">
    </td>
    <td>
        <input id="save" type="button" value="保存" style="border: 1px solid lightskyblue;" onclick="save()">
    </td>
    <td>
        <input id="count"  type="button" value="统计商品数量" style="border: 1px solid lightskyblue;" onclick="count()"/>
    </td>
    <td>
        <input type="button"  name="" value="导出饼图" style="border: 1px solid lightskyblue;text-align: center;width: 80px"onclick="exportChart()">
    </td>
</tr>
</div>
</body>
</html>
