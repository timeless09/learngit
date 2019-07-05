<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑班次</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addLeave() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1"
            }
            $.ajax({
                type: "POST",
                url: "../AddClassServlet",
                data: {
                    Class_Code: $("#Class_Code").val(),
                    Class_Name: $("#Class_Name").val(),
                    Class_Start_Time: $("#Class_Start_Time").val(),
                    Class_End_Time: $("#Class_End_Time").val(),
                    Class_Note: $("#Class_Note").val(),
                    Class_ID: s,
                },
                success: function () {
                    window.location.href = "../classes.jsp"
                },
                error: function (data) {
                    console.log(data);//在前端控制台打印请求的状态
                }
            });
        }
    </script>
    <script type="text/javascript">
        window.onload = function () {
            var s = "${param.id}";
            if (s != "") {
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "../GetClassServlet",
                    data: {s},
                    success: function (data) {
                        //var json = eval("("+data+")");
                        $("#Class_Code").val(data.name);
                        $("#Class_Name").val(data.startTime);
                        $("#Class_Start_Time").val(data.endTime);
                        $("#Class_End_Time").val(data.reason);
                        $("#Class_Note").val(data.reason);
                    }
                });

            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">班次编码：</td><td><input type="text"  id="Class_Code"/></td></tr>
                <tr><td align="right">班次名称：</td><td><input type="text" id="Class_Name"/></td></tr>
                <tr><td align="right">早上上班时间：</td><td><input type="text" id="Class_Start_Time"/></td></tr>
                <tr><td align="right">下午下班时间：</td><td><input type="text" id="Class_End_Time"/></td></tr>
                <tr><td align="right">备注：</td><td><textarea cols="40" rows="4" id="Class_Note"></textarea></td> </tr>   
                <tr><td></td><td align="left"><input type="button" onclick="addLeave()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>
