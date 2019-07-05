<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑岗位</title>
    </head>
    <script type="text/javascript" src="../assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function addStation() {
//            alert($("#Employees_ID_Code").val());

            var s = "${param.id}";
            if (s === "") {
                s = "-1";
            }
            $.ajax({
                type: "POST",
                url: "../AddStationServlet",
                data: {
                    Station_Code: $("#Station_Code").val(),
                    Station_Name: $("#Station_Name").val(),
                    Station_Department: $("#Station_Department").val(),
                    Station_Superior: $("#Station_Superior").val(),
                    Station_Category: $("#Station_Category").val(),
                    Station_Describe: $("#Station_Describe").val(),
                    Station_ID: s
                },
                success: function () {
                    window.location.href = "../station.jsp";
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
                    url: "../GetStationServlet",
                    data: {s},
                    success: function (data) {
                        $("#Station_Code").val(data.code);
                        $("#Station_Name").val(data.name);
                        $("#Station_Department").val(data.department);
                        $("#Station_Superior").val(data.superior);
                        $("#Station_Category").val(data.category);
                        $("#Station_Describe").val(data.describe);
                    }
                });
            }
        }
    </script>
    <body>
        <form>
            <table border="0" align="center" width="580">
                <tr><td align="right">岗位编码：</td><td><input type="text"  id="Station_Code"/></td></tr>
                <tr><td align="right">岗位名称：</td><td><input type="text" id="Station_Name"/></td></tr>
                <tr><td align="right">所在部门：</td><td><select id="Station_Department"  style="width:170px">
                            <option value ="总裁室">总裁室</option>
                            <option value ="财务部">财务部</option>
                            <option value ="人力资源">人力资源</option>
                        </select></td> </tr>  
                <tr><td align="right">直接上级：</td><td><select id="Station_Superior"  style="width:170px">
                            <option value ="CEO">CEO-总裁室</option>
                            <option value ="KJ">KJ-财务部</option>
                            <option value ="QT">QT-人力资源</option>
                            <option value ="HQ">HQ-人力资源</option>
                        </select></td> </tr>  
                <tr><td align="right">岗位类别：</td><td><select id="Station_Category"  style="width:170px">
                            <option value ="管理类">管理类</option>
                            <option value ="技术类">技术类</option>
                            <option value="文职类">文职类</option>
                        </select></td> </tr>  
                <tr><td align="right">岗位职责：</td><td><textarea cols="40" rows="4" id="Station_Describe"></textarea></td> </tr>   
                <tr><td></td><td align="left"><input type="button" onclick="addStation()" value="保存"/>&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="javascript:history.go(-1)" value="返回"/></td> </tr>   
            </table>
        </form>
    </body>
</html>
