<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑补卡记录</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addRepairCard() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1";
            }
            $.ajax({
                type: "POST",
                url: "../AddRepairCardServlet",
                data: {
                    Repair_Person_Code: $("#Repair_Person_Code").val(),
                    Repair_Date: $("#Repair_Date").val(),
                    Repair_Reason: $("#Repair_Reason").val(),
                    Repair_ID: s
                },
                success: function () {
                    window.location.href = "../repair.jsp";
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
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "../GetRepairCardServlet",
                    data: {s},
                    success: function (data) {
                        //var json = eval("("+data+")");
                        $("#Repair_Person_Code").val(data.name);
                        $("#Repair_Date").val(data.date);
                        $("#Repair_Reason").val(data.reason);
                    }
                });

            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">补卡人：</td><td><input type="text"  id="Repair_Person_Code" value="${userName}"/></td></tr>
                <tr><td align="right">补卡时间：</td><td><input type="datetime-local" id="Repair_Date"/></td></tr>
                <tr><td align="right">补卡原因：</td><td><textarea cols="40" rows="4" id="Repair_Reason"></textarea></td> </tr>   
                <tr><td></td><td align="left"><input type="button"  onclick="addRepairCard()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>
