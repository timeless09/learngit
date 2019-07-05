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
        <title>考勤表</title>
    </head>

    <script type="text/javascript">
        window.onload = function () {
            var select = "${param.usernameid}";
            var start = "${param.start}";
            var end = "${param.end}";
            var flag = "${param.flag}";
            console.log(flag);
            if (select === "" && flag !== "1") {
                select = "%";
            }
            console.log(select);
            if (end === "") {
                end = "*";
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "SearchCheckReportServlet",
                data: {select, start, end},
                success: function (data) {
                    getDate(data);
                }
            });
        };

        function getDate(data) {
            $("#checkReport_length").html("共有数据：" + data.length + "  条");
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
                c4.innerHTML = data[i].start;
                var c5 = row.insertCell(5);
                c5.innerHTML = data[i].end;
                var c6 = row.insertCell(6);
                c6.innerHTML = data[i].condition;
            }
            //alert(table.rows.length);
            paging();//分页
        }

        function search() {
            //            alert(1);
            window.location.href = "attendance.jsp?flag=" + 1 + "&usernameid=" + $("#usernameid").val() +
                    "&start=" + $("#start").val() + "&end=" + $("#end").val();
            ;

        }
    </script>


    <body align="center">

        <!-------------------------------------------------->
        <div class="weadmin-body">
            <div class="layui-row">
                <form class="layui-form layui-col-md12 we-search">
                    <div class="layui-inline">
                        <input class="layui-input" type="date" placeholder="开始日" name="start" id="start">
                    </div>
                    <div class="layui-inline">
                        <input class="layui-input" type="date" placeholder="截止日" name="end" id="end">
                    </div>
                    <div class="layui-inline">
                        <input type="text" id="usernameid" placeholder="请输入员工编码或员工姓名" autocomplete="off" class="layui-input"/>
                    </div>
                    <input class="layui-btn" type="button" onclick="search()" value="搜索">
                </form>
            </div>
            <div>
                <div style="float: left;">
                    <span class="fr" style="line-height:40px" id="checkReport_length"></span>
                </div>
                <div style="width: 100%;text-align: right">
                    <div class="weadmin-block">
                        <form name="export" action="SearchCheckReportServlet" method="get" style=" margin: 0px;display: inline">
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
                        <th>员工编码</th>
                        <th>员工姓名</th>
                        <th>早上上班时间</th>
                        <th>下午下班时间</th>
                        <th>出勤情况</th>
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
    </body>
</html>
