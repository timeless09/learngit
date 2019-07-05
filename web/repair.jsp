<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Styles -->
    <link href="assets/css/lib/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/lib/themify-icons.css" rel="stylesheet">
    <link href="assets/css/lib/menubar/sidebar.css" rel="stylesheet">
    <link href="assets/css/lib/bootstrap.min.css" rel="stylesheet">

    <link href="assets/css/lib/helper.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/layui.css" rel="stylesheet">

    <title>补卡表</title>
</head>
<script>
    window.onload = function () {
        var select = "${param.usernameid}";
        if (select === "") {
            select = "%";
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "SearchRepairCardServlet",
            data: {select},
            success: function (data) {
                getDate(data, "edit_repairCard", "DeleteRepairCardServlet");
            }
        });
    };

    function getDate(data, local, servlet) {
        $("#repairCard_length").html("共有数据：" + data.length + "  条");
        var table = document.getElementById("table");
        for (var i = 0; i < data.length; i++) {
            var row = table.insertRow(table.rows.length);
            //
            var c0 = row.insertCell(0);
            c0.innerHTML = "<input type='checkbox' name = 'checkbox' value = '" + data[i].id + "'/>";
            var c1 = row.insertCell(1);
            c1.innerHTML = data[i].id;
            var c2 = row.insertCell(2);
            c2.innerHTML = data[i].code;
            var c3 = row.insertCell(3);
            c3.innerHTML = data[i].name;
            var c4 = row.insertCell(4);
            c4.innerHTML = data[i].date;
            var c5 = row.insertCell(5);
            c5.innerHTML = "<input type='button' value='更新' class='layui-btn' onclick='update(" + data[i].id + ',\"' + local + "\")'/>&nbsp;&nbsp;&nbsp;"
                + "<input type='button' value='删除' class='layui-btn' onclick='del(" + data[i].id + ',\"' + servlet + "\")'/>";
        }
        //alert(table.rows.length);
        paging();//分页
    }

</script>

<body align="center">

<!-------------------------------------------------->
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            <div class="layui-inline">
                <input type="text" id="usernameid" placeholder="请输入补卡人编码或姓名" autocomplete="off" class="layui-input"/>
            </div>
            <input class="layui-btn" type="button" onclick="search('repair')" value="搜索">
        </form>
    </div>
    <div>
        <div style="float: left;">
            <span class="fr" style="line-height:40px" id="repairCard_length"></span>
        </div>
        <div style="width: 100%;text-align: right">
            <div class="weadmin-block">

                <button class="layui-btn layui-btn-danger" onclick="delAll('DeleteRepairCardServlet')">批量删除</button>
                <a href="./edit/edit_repairCard.jsp">
                    <button class="layui-btn">添加</button>
                </a>

                <form name="export" action="SearchRepairCardServlet" method="get" style=" margin: 0px;display: inline">
                    <input class="layui-btn layui-btn" type="submit" value="导出"/>
                </form>
            </div>
        </div>
    </div>

    <table class="layui-table" id="table">
        <thead>
        <tr>
            <th>选择</th>
            <th>ID</th>
            <th>补卡人编码</th>
            <th>补卡人姓名</th>
            <th>补卡日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr hidden="true">
        </tr>
        </tbody>
    </table>

</div>
<div id="pagiDiv" align="center">
    <span id="spanFirst" class="page">首页</span> &nbsp;
    <span id="spanPre" class="page">上一页</span> &nbsp;
    <span id="spanNext" class="page">下一页</span> &nbsp;
    <span id="spanLast" class="page">末页</span> &nbsp;
    第 <span id="spanPageNum"></span> 页/共 <span id="spanTotalPage"></span> 页
</div>
<script type="text/javascript" src="assets/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="assets/js/pagination.js"></script>
<script type="text/javascript" src="assets/js/operation.js"></script>
</body>
</html>
