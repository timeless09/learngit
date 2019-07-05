<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑部门</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addDepartment() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1";
            }
            $.ajax({
                type: "POST",
                url: "../AddDepartmentServlet",
                data: {
                    Department_ID_Code: $("#Department_ID_Code").val(),
                    Department_Name: $("#Department_Name").val(),
                    Department_Leader: $("#Department_Leader").val(),
                    Department_Duty: $("#Department_Duty").val(),
                    Department_Superior_Name: $("#Department_Superior_Name").val(),
                    Department_ID: s
                },
                success: function () {
                    window.location.href = "../department.jsp";
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
                    url: "../GetDepartmentServlet",
                    data: {s},
                    success: function (data) {
                        $("#Department_ID_Code").val(data.code);
                        $("#Department_Name").val(data.name);
                        $("#Department_Leader").val(data.leader);
                        $("#Department_Duty").val(data.duty);
                        $("#Department_Superior_Name").val(data.superior_Name);
                    }
                });

            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">部门编码：</td><td><input type="text"  id="Department_ID_Code"/></td></tr>
                <tr><td align="right">部门名称：</td><td><input type="text" id="Department_Name"/></td></tr>
                <tr><td align="right">部门负责人：</td><td><input type="text" id="Department_Leader"/></td> </tr>  
                <tr><td align="right">部门职责：</td><td><input type="text" id="Department_Duty"/></td></tr>
                <tr><td align="right">上级部门：</td><td><input type="text" id="Department_Superior_Name"/></td></tr>
                <!--                <tr><td align="right">备注：</td><td><textarea cols="40" rows="4" id="Department_Note"></textarea></td> </tr>   -->
                <tr><td></td><td align="left"><input type="button" onclick="addDepartment()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>
