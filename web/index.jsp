<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>考勤系统</title>
    <!-- Styles -->
    <link href="assets/css/lib/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/lib/themify-icons.css" rel="stylesheet">
    <link href="assets/css/lib/owl.carousel.min.css" rel="stylesheet"/>
    <link href="assets/css/lib/owl.theme.default.min.css" rel="stylesheet"/>
    <link href="assets/css/lib/menubar/sidebar.css" rel="stylesheet">
    <link href="assets/css/lib/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/lib/helper.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <script src="assets/js/PageJump.js"></script>
</head>

<body>
<div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures">
    <div class="nano">
        <div class="nano-content">
            <ul>
                <div class="logo"><a onclick="welcome()">
                    <!-- <img src="assets/images/logo.png" alt="" /> --><span>考勤</span></a></div>

                <li><a class="sidebar-sub-toggle"><i class="ti-bar-chart-alt"></i> 公司架构 <span
                        class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a onclick="employees()">员工</a></li>
                        <li><a onclick="station()">岗位</a></li>
                        <li><a onclick="department()">部门</a></li>
                    </ul>
                </li>

                <li><a class="sidebar-sub-toggle"><i class="ti-layout"></i> 考勤数据 <span
                        class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a onclick="punchCard()">打卡单</a></li>
                        <li><a onclick="repair()">补卡单</a></li>
                        <li><a onclick="leave()">请假单</a></li>
                    </ul>
                </li>
                <li><a class="sidebar-sub-toggle"><i class="ti-panel"></i> 考勤报表 <span
                        class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a onclick="attendance()">考勤表</a></li>
                    </ul>
                </li>
                <li><a class="sidebar-sub-toggle"><i class="ti-layout-grid4-alt"></i> 考勤设置 <span
                        class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a onclick="classes()">班次</a></li>
                    </ul>
                </li>
                <li><a class="sidebar-sub-toggle"><i class="ti-heart"></i> 财务管理 <span
                        class="sidebar-collapse-icon ti-angle-down"></span></a>
                    <ul>
                        <li><a onclick="paySalary()">派薪单</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="float-left">
                    <div class="hamburger sidebar-toggle">
                        <span class="line"></span>
                        <span class="line"></span>
                        <span class="line"></span>
                    </div>
                </div>
                <div class="float-right">

                    <div class="dropdown dib">
                        <div class="header-icon" data-toggle="dropdown">
                                    <span class="user-avatar">${userName}
                                        <i class="ti-angle-down f-s-10"></i>
                                    </span>
                            <div class="drop-down dropdown-profile dropdown-menu dropdown-menu-right">
                                <ul>
                                    <li>
                                        <a onclick="personalInformation()">
                                            <i class="ti-user"></i>
                                            <span>个人信息</span>
                                        </a>

                                    </li>

                                    <li>
                                        <a href="javascript:void(0);" onclick="window.location.href = 'login.jsp'"
                                           target="_blank">
                                            <i class="loginout"></i>
                                            <span>退出</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--欢迎语 当前路径-->
<div class="content-wrap">
    <div class="main">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-8 p-r-0 title-margin-right">
                    <div class="page-header">
                        <div class="page-title">
                            <h1>欢迎, <span>${userName}</span></h1>
                        </div>
                    </div>
                </div>
                <!-- /# column -->
                <div class="col-lg-4 p-l-0 title-margin-left">
                    <div class="page-header">
                        <div class="page-title">
                            <ol class="breadcrumb">
                                <a href="index.jsp">回到首页</a>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- /# column -->
            </div>
            <!-- /# row -->
            <section id="main-content">
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe id="iframe" src='./welcome.jsp' frameborder="0" scrolling="no" class="weIframe"
                                width="100%" height="1000" onload="changeFrameHeight()"></iframe>

                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

<!-- jquery vendor -->
<script src="assets/js/lib/jquery.min.js"></script>
<script src="assets/js/lib/jquery.nanoscroller.min.js"></script>
<!-- nano scroller -->
<script src="assets/js/lib/menubar/sidebar.js"></script>
<script src="assets/js/lib/preloader/pace.min.js"></script>
<!-- sidebar -->

<script src="assets/js/lib/bootstrap.min.js"></script>
<script src="assets/js/scripts.js"></script>
<!-- bootstrap -->

<!-- scripit init-->
<script src="assets/js/dashboard2.js"></script>

</body>

</html>
