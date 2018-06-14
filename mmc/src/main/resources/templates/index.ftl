<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layUI-运营管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- 页面描述 -->
    <meta name="description" content="CIC"/>
    <!-- 页面关键词 -->
    <meta name="keywords" content="CIC"/>
    <!-- 网页作者 -->
    <meta name="author" content="CIC"/>
    <link rel="icon" href="#">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="/static/css/main.css" media="all" />
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main">
            <a href="#" class="logo" >layUI运营后台</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:" class="iconfont hideMenu icon-menu1"></a>

            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item showNotice" id="showNotice" pc>
                    <a href="javascript:"><i class="iconfont icon-gonggao"></i><cite>系统公告</cite></a>
                </li>
            <#--<li class="layui-nav-item lockcms" pc>-->
            <#--<a href="javascript:"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>-->
            <#--</li>-->
                <li class="layui-nav-item" pc>
                    <a href="javascript:">
                        <img src="<#if currentUser.icon?? && currentUser.icon!=''>${currentUser.icon}<#else>/static/images/face.jpg</#if>" class="layui-circle" width="35" height="35">
                        <cite><#if currentUser.loginName?? && currentUser.loginName!=''>${currentUser.loginName}<#else>${currentUser.nameZh}</#if></cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" data-url="/sysUser/userInfo"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
                        <dd><a href="javascript:" data-url="/sysUser/changePassword"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
                        <dd><a href="javascript:" class="changeSkin"><i class="iconfont icon-huanfu"></i><cite>修改皮肤</cite></a></dd>
                        <dd><a href="/login/logout" class="signOut"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的图像" ><img src="<#if currentUser.icon?? && currentUser.icon!=''>${currentUser.icon}<#else>/static/images/face.jpg</#if>"></a>
            <p>你好!<span class="userName"><#if currentUser.loginName?? && currentUser.loginName!=''>${currentUser.loginName}<#else>${currentUser.nameZh}</#if></span>,欢迎登录</p>
        </div>
        <div class="navBar layui-side-scroll"></div>
    </div>

    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:"><i class="iconfont icon-caozuo"></i>页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i>刷新当前</a></dd>
                        <dd><a href="javascript:" class="closePageOther"><i class="iconfont icon-prohibit"></i>关闭其他</a></dd>
                        <dd><a href="javascript:" class="closePageAll"><i class="iconfont icon-guanbi"></i>关闭全部</a></dd>
                    </dl>
                </li>
            </ul>

            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="/main"></iframe>
                </div>
            </div>
        </div>
    </div>

    <!-- 底部 -->
    <div class="layui-footer footer">
        <p>Copyright © 2018<a href="#" target="_blank"> </a></p>
    </div>

</div>

<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>

<script>
    var baseUrl = "${basePath}";
</script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/leftNav.js?v=2.0"></script>
<script type="text/javascript" src="/static/js/index.js?t=3.0"></script>
</body>
</html>