<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑派薪记录</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addPaySalary() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1";
            }
            $.ajax({
                type: "POST",
                url: "../AddPaySalaryServlet",
                data: {
                    Salary_Person_Code: $("#Salary_Person_Code").val(),
                    Salary_Start_Time: $("#Salary_Start_Time").val(),
                    Salary_End_Time: $("#Salary_End_Time").val(),
                    Salary: $("#Salary").val(),
                    Salary_ID: s
                },
                success: function () {
                    window.location.href = "../paySalary.jsp";
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
            if (s !== "") {
                $("#Salary").attr("readonly", "true");
                document.getElementById("cal").type = "button";

                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "../GetPaySalaryServlet",
                    data: {s},
                    success: function (data) {
                        //var json = eval("("+data+")");
                        $("#Salary_Person_Code").val(data.name);
                        $("#Salary_Start_Time").val(data.start);
                        $("#Salary_End_Time").val(data.end);
                        $("#Salary").val(data.salary);
                        $("#Salary_Time").val(data.hour);
                    }
                });
            } else {
                $("#Salary").removeAttr("readonly");
                document.getElementById("cal").type = "hidden";
            }
        };
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
            var mytime = year + "/" + time(mon) + "/" + time(day) + " " + time(hour) + ":" + time(minutes) + ":" + time(seconds);
            $("#Salary_Time").val(mytime);
        });
        function time(d) {
            if (d >= 0 && d <= 9) {
                d = "0" + d;
            }
            return d;
        }
    </script>
    <script>
        function cal_Salary() {
            var salary = $("#Salary").val();
            var hours = $("#Salary_Time").val();
            var newSalary = salary * hours / (30 * 9);
            $("#Salary").val(newSalary.toFixed(2));
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">领薪人：</td><td><input type="text"  id="Salary_Person_Code"/></td></tr>
                <tr><td align="right">领薪时间：</td><td><input  id="Salary_Time" type="text"/></tr>
                <tr><td align="right">领薪区间：</td><td><input type="datetime-local" id="Salary_Start_Time"/></td></tr>
                <tr><td></td><td> <input type="datetime-local" id="Salary_End_Time"/></td></tr>
                <tr><td align="right">薪水：</td><td><input type="text" id="Salary"/></td></tr>
                <tr hidden="true"><td align="right">领薪总时间：</td><td><input type="text" id="Salary_Time"/></td></tr>
                <tr><td></td><td align="left"><input type="button" id="cal"  onclick="cal_Salary()" value="计算薪水"/>
                        &nbsp;&nbsp;&nbsp;<input type="button"  onclick="addPaySalary()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>

