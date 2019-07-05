<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加打卡记录</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addPunchCard() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1";
            }
            $.ajax({
                type: "POST",
                url: "../AddPunchCardServlet",
                data: {
                    PunchCard_Person: $("#PunchCard_Person").val(),
                    PunchCard_Date: $("#PunchCard_Date").val(),
                    PunchCard_Note: $("#PunchCard_Note").val(),
                    Leave_ID: s
                },
                success: function () {
                    window.location.href = "../punchCard.jsp";
                },
                error: function (data) {
                    console.log(data);//在前端控制台打印请求的状态
                }
            });

        }
    </script>
    <script type="text/javascript">
        $(function () {
            var date = new Date();
            var year = date.getFullYear(); //获取当前年份
            var mon = date.getMonth() + 1; //获取当前月份
            var day = date.getDate(); //获取当前日
            var hour = date.getHours();
            var minutes = date.getMinutes();
            var seconds = date.getSeconds();
            var mytime = year + "-" + time(mon) + "-" + time(day) + "T"
                    + time(hour) + ":" + time(minutes) + ":" + time(seconds) + ".0";
            $("#PunchCard_Date").val(mytime);
        });
        function time(d) {
            if (d >= 0 && d <= 9) {
                d = "0" + d;
            }
            return d;
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">打卡人：</td><td><input type="text" id="PunchCard_Person" value="${userName}" /></td></tr>
                <tr><td align="right">打卡时间：</td><td><input type="datetime-local" id="PunchCard_Date"/></td></tr>
                <tr><td align="right">备注：</td><td><input type="text" id="PunchCard_Note"/></td></tr>
                <tr><td></td><td align="left"><input type="button" onclick="addPunchCard()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>
