<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看打卡信息</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var s = "${param.id}";
            if (s !== "") {
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "../GetPunchCardServlet",
                    data: {s},
                    success: function (data) {
                        $("#PunchCard_Person").val(data.pc_person_name);
                        $("#PunchCard_Date").val(data.pc_date);
                        $("#PunchCard_Note").val(data.pc_note);
                    }
                });
            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">打卡人：</td><td><input type="text" id="PunchCard_Person" readonly="true"/></td></tr>
                <tr><td align="right">打卡时间：</td><td><input type="text" id="PunchCard_Date" readonly="true"/></td></tr>
                <tr><td align="right">备注：</td><td><input type="text" id="PunchCard_Note" readonly="true"/></td></tr>
                <tr><td></td><td align="left"><input type="button" onclick="javascript:history.go(-1)" value="返回"/></td></tr>
            </table>
        </form>

    </body>
</html>
