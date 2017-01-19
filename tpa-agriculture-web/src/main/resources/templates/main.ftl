<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TPA-
        <sitemesh:write property='title'/>
    </title>
    <script type="text/ecmascript" src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="${request.contextPath}/static/js/i18n/grid.locale-en.js"></script>
    <!-- This is the Javascript file of jqGrid -->
    <script type="text/ecmascript" src="${request.contextPath}/static/js/jquery.jqGrid.min.js"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <!--<link rel="stylesheet" href="${request.contextPath}/static/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${request.contextPath}/static/css/ui.jqgrid-bootstrap.css"/>
    <script>
        $.jgrid.defaults.width = 1140;
        $.jgrid.defaults.height = 560;
        $.jgrid.defaults.styleUI = 'Bootstrap';
    </script>
    <script src="${request.contextPath}/static/js/bootstrap.min.js"></script>
    <style type="text/css">
        .navbar{
            margin-bottom: 0px;
        }
    </style>
    <sitemesh:write property='head'/>
</head>
<body>
<div class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            <#--<a class="navbar-brand" href="/bbs/bbs/index">-->
            <#--<svg viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1880"><path d="M832.299957 714.965621c-71.047126 88.067778-148.2126 70.077032-148.2126 70.077032 77.615729-65.686025 74.107835-233.597269 74.107835-233.597269 56.979736 6.564509 74.104765 93.439112 74.104765 93.439112 2.635013 53.857629 148.21567 93.439112 148.21567 93.439112C887.304711 721.78698 832.299957 714.965621 832.299957 714.965621zM513.173732 467.12086c0 0-26.707268-15.610536-21.554922-82.356706 0 0 43.672662-370.594433 237.145072-313.196165 0 0 128.243803 88.283696-21.580504 292.610314C707.182354 364.178303 570.780755 504.72634 513.173732 467.12086zM560.578392 504.72634c31.834033 103.172801-49.409316 210.239282-49.409316 210.239282-95.057983 32.713053-67.633377-56.756655-67.633377-56.756655 58.984393-61.013609 42.930765-130.125663 42.930765-130.125663C512.189312 506.013659 560.578392 504.72634 560.578392 504.72634zM214.740806 668.246577c0 0-15.826454-68.054979 9.243525-99.791797 0 0 19.540032 53.137221 64.86431 6.345522 0 0 13.948687-61.868069-50.226938-47.768957 0 0 21.086248-69.096704 66.274426-103.007025-82.760912-100.30652-238.370992-82.821287-238.370992-82.821287-51.842739-30.236652 0-46.715975 0-46.715975 135.454018-0.991584 222.324528 93.439112 222.324528 93.439112-23.989367-126.739543-148.21567-163.520237-148.21567-163.520237-39.818891-48.290843 17.485233-35.68371 17.485233-35.68371 126.141932 81.534991 149.874449 190.310394 154.345274 230.244918 20.531616-12.791327 45.414329-18.281365 75.196633-7.67889 0 0 156.796092 73.606415-24.702612 256.95935C362.956476 668.246577 246.483764 776.989233 214.740806 668.246577zM706.990996 513.219781c30.182416 116.948549-22.903639 201.74584-22.903639 201.74584-59.500139 125.21891-148.21567 46.719044-148.21567 46.719044 93.910856-94.708012 98.807377-256.95935 98.807377-256.95935C675.925467 502.981602 706.990996 513.219781 706.990996 513.219781z" p-id="1881"></path></svg>-->
            <#--Beetl-BBS-->
            <#--</a>-->
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#">首页综合</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">板块 <span
                                class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">AAA </a></li>
                            <li><a href="#">BBB </a></li>
                            <li><a href="#">CCC </a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" id="keyword" placeholder="关键字搜索">
                    </div>
                    <button type="button" class="btn btn-default" id="serach-btn">搜索</button>
                </form>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#login-dialog" data-toggle="modal"><i class="fa fa-sign-in"></i> 登录</a></li>
                    <li><a href="#reg-dialog" data-toggle="modal"><i class="fa fa-user-plus"></i> 注册</a></li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <sitemesh:write property='body'/>
</div>
</body>
</html>