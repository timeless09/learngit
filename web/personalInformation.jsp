<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>个人信息</title>

</head>
<script>
    window.onload = function () {
        var s = "${userName}";
        $.ajax({
            type: "post",
            dataType: "json",
            url: "PersonalInfoServlet",
            data: {s},
            success: function (data) {
                //var json = eval("("+data+")");

                $("#Employees_ID_Code").val(data.code);
                $("#Employees_Name").val(data.name);
                $("#Employees_Sex").val(data.sex);
                $("#Employees_Age").val(data.age);
                $("#Employees_Nation").val(data.nation);
                $("#Employees_ID_Card").val(data.idCard);
                $("#Employees_Salary").val(data.salary);
                $("#Employees_Phone").val(data.phone);
                $("#Employees_Urgent_Name").val(data.urgentName);
                $("#Employees_Urgent_Phone").val(data.urgentPhone);
                $("#Employees_Station_ID").val(data.stationID);
                $("#Employees_Self_Describe").val(data.selfDes);
            }
        });

    }
</script>
<body>
<form>
    <table border="0" align="center" width="580">
        <tr>
            <td align="right">员工编码：</td>
            <td><input readonly="true" type="text" id="Employees_ID_Code"/></td>
        </tr>
        <tr>
            <td align="right">姓名：</td>
            <td><input readonly="true" type="text" id="Employees_Name"/></td>
        </tr>
        <tr>
            <td align="right">性别：</td>
            <td><input readonly="true" type="text" id="Employees_Sex"/></td>
        </tr>
        <tr>
            <td align="right">年龄：</td>
            <td><input readonly="true" type="text" id="Employees_Age"/></td>
        </tr>
        <tr>
            <td align="right">民族：</td>
            <td><input readonly="true" type="text" id="Employees_Nation"/></td>
        </tr>
        <tr>
            <td align="right">身份证：</td>
            <td><input readonly="true" type="text" id="Employees_ID_Card"/></td>
        </tr>
        <tr>
            <td align="right">薪水：</td>
            <td><input readonly="true" type="text" id="Employees_Salary"/></td>
        </tr>
        <tr>
            <td align="right">联系电话：</td>
            <td><input readonly="true" type="text" id="Employees_Phone"/></td>
        </tr>
        <tr>
            <td align="right">紧急联系人：</td>
            <td><input readonly="true" type="text" id="Employees_Urgent_Name"/></td>
        </tr>
        <tr>
            <td align="right">紧急联系人电话：</td>
            <td><input readonly="true" type="text" id="Employees_Urgent_Phone"/></td>
        </tr>
        <tr>
            <td align="right">岗位：</td>
            <td><select disabled="true" id="Employees_Station_ID" style="width:170px">
                <option value="1">CEO</option>
                <option value="2">CFO</option>
                <option value="3">CTO</option>
            </select></td>
        </tr>
        <tr>
            <td align="right">个人描述：</td>
            <td><textarea readonly="true" cols="40" rows="4" id="Employees_Self_Describe"></textarea></td>
        </tr>

    </table>
    <div align="center">
        <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
    </div>
</form>
<script type="text/javascript" src="assets/js/jquery-1.12.4.min.js"></script>
</body>
</html>
