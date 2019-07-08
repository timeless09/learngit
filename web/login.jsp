<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录界面</title>
    <link rel="stylesheet" type="text/css" href="./assets/css/dmaku2.css"/>
</head>
<script type="text/javascript">
    function login() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        $.ajax({
            type: "POST",
            url: "LoginServlet",
            data: {username: username, password: password},
            dataType: 'text',
            success: function (data) {
                if (data === "1") {
                    window.location.assign("index.jsp");
                }else if(data === "2"){
                    alert("账号或密码错误");
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    }


</script>
<body>

<header id="header">
    <a href="javascript:;" class="logo"></a>
    <i class="icons">beta</i>
</header><!-- /header -->

<!-- 页面主体START -->
<section id="main">
    <h1>登录</h1>
    <form>

        <div class="clearfix" data-propertyname="username" data-controltype="Phone">
            <input type="text" placeholder="输入帐号" id="username" name="" placeholder="" data-required="required"
                   autocomplete="off">
        </div>

        <div class="clearfix" data-propertyname="password" data-controltype="Password">
            <input type="password" id="password" name="password" placeholder="输入密码" data-required="required"
                   autocomplete="off">
        </div>

        <div class="clearfix btn_login" data-propertyname="submit" data-controltype="Botton">
            <input type="button" value="登录" onclick="login()">
        </div>

    </form>
</section>
<!-- 页面主体END -->

<footer id="footer">


</footer>
<script type="text/javascript" src="assets/js/jquery-1.12.4.min.js"></script>
</body>
</html>
