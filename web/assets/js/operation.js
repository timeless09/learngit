//搜索
function search(local) {
    window.location.href = "" + local + ".jsp?flag=" + 1 + "&usernameid=" + $("#usernameid").val();

}
//更新
function update(data, local) {
    window.location.href = "./edit/" + local + ".jsp?id=" + data;
}
//单个删除
function del(data, servlet) {
    if (window.confirm("确认删除吗？")) {
        $.ajax({
            type: "post",
            url: servlet, //DeleteRepairCardServlet
            data: {data},
            success: function () {
                window.location.reload();
            }
        });
    }
}
//多个删除
function delAll(servlet) {
    if (window.confirm("确认删除吗？")) {
        var check = document.getElementsByName("checkbox");
        var s = "";
        for (var i = 0; i < check.length; i++) {
            if (check[i].checked) {

                s = s + $(check[i]).val() + "a";
            }
        }
        $.ajax({
            type: "post",
            url: servlet,
            data: {s},
            success: function () {
                window.location.reload();
            }
        });
    }
}

//查看
function check(data) {
    window.location.href = "./edit/check_punchcard.jsp?id=" + data;
}
