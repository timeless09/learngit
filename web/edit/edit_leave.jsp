<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑请假记录</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        //对请假时间为30分钟的倍数进行判断   
        function onblus() {
            var startTime = new Date($("#Leave_Start_Time").val()).getTime();
            var endTime = new Date($("#Leave_End_Time").val()).getTime();
            var space = (endTime - startTime) / 1000 / 60;
            if (isNaN(startTime)) {
                alert("请输入开始时间！");
            } else {
                if (space >= 0) {
                    if (space % 30 !== 0) {
                        alert("请假的时长必须是半个小时的整数倍！");
                    }
                } else {
                    alert("请假的结束时间必须晚于开始时间！");
                }

            }
        }
        function addLeave() {
            var startTime = new Date($("#Leave_Start_Time").val()).getTime();
            var endTime = new Date($("#Leave_End_Time").val()).getTime();
            var space = (endTime - startTime) / 1000 / 60;
            if (space >= 0) {
                if (space % 30 === 0) {
                    var s = "${param.id}";
                    if (s === "") {
                        s = "-1";
                    }
                    $.ajax({
                        type: "POST",
                        url: "../AddLeaveServlet",
                        data: {
                            Leave_Person: $("#Leave_Person").val(),
                            Leave_Start_Time: $("#Leave_Start_Time").val(),
                            Leave_End_Time: $("#Leave_End_Time").val(),
                            Leave_Reason: $("#Leave_Reason").val(),
                            Leave_ID: s
                        },
                        success: function () {
                            window.location.href = "../leave.jsp";
                        },
                        error: function (data) {
                            console.log(data); //在前端控制台打印请求的状态
                        }
                    });
                } else {
                    alert("请假的时长必须是半个小时的整数倍！");
                }
            } else {
                alert("请假的结束时间必须晚于开始时间！");
            }
        }
    </script>
    <script type="text/javascript">
        window.onload = function () {
            var s = "${param.id}";
            if (s !== "") {
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "../GetLeaveServlet",
                    data: {s},
                    success: function (data) {
                        //var json = eval("("+data+")");
                        $("#Leave_Person").val(data.name);
                        $("#Leave_Start_Time").val(data.startTime);
                        $("#Leave_End_Time").val(data.endTime);
                        $("#Leave_Reason").val(data.reason);
                    }
                });
            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">请假人：</td><td><input type="text"  id="Leave_Person"/></td></tr>
                <tr><td align="right">请假时间：</td><td><input type="datetime-local" id="Leave_Start_Time"/></td></tr>
                <tr><td></td><td> <input type="datetime-local" onblur='onblus()' id="Leave_End_Time"/></td></tr>
                <tr><td align="right">请假原因：</td><td><textarea cols="40" rows="4" id="Leave_Reason"></textarea></td> </tr> 
                <tr><td></td><td align="left"><input type="button" onclick="addLeave()" value="保存"/>&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>
            </table>
        </form>
    </body>
</html>
