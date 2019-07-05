<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>欢迎</title>
        <link href="assets/css/style.css" rel="stylesheet">
    </head>
    <script type="text/javascript">
        window.onload = function () {
            setInterval(function () {
                var date = new Date();
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var hour = date.getHours();
                var minutes = date.getMinutes();
                var seconds = date.getSeconds();
                var weekArray = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
                var week = weekArray[date.getDay()];
                var d = document.getElementById('Date');
                d.innerHTML = "当前时间：" + time(year) + "-" + time(month) + "-" + time(day) +
                        " " + time(hour) + ":" + time(minutes) + ":" + time(seconds) + "  " + week;
            }, 1000);

            function time(d) {
                if (d >= 0 && d <= 9) {
                    d = "0" + d;
                }
                return d;
            }
        };
    </script>
    <body>
        <div class="card">
            <h2>欢迎：${userName}！</h2>
            <h3>
                <div id="Date"/>
            </h3>
        </div>
        <div class="card">
            <div class="card-title">
                <h2>开发团队 </h2>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>版权所有</th>
                                <th>开发者</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>福建农林大学软件工程专业</td>
                                <td>林顺阳 温福良 林嘉辉 张宗锦</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="assets/js/jquery-1.12.4.min.js"></script>
    </body>
</html>
