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
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css" media="all">
    <#--<link rel="stylesheet" href="/static/plugins/font-awesome/css/font-awesome.min.css" media="all">-->
    <link rel="stylesheet" href="/static/build/css/app.css" media="all">
</head>

<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">KIT ADMIN</div>
        <div class="layui-logo kit-logo-mobile">K</div>
        <ul class="layui-nav layui-layout-left kit-nav" kit-one-level>
            <li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
            <li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item"><a href="javascript:;" id="pay"><i class="fa fa-gratipay" aria-hidden="true"></i> 捐赠我</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> Van
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar id="menuList">
                <#list menuList! as menu>
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span>  ${menu.name!}</span></a>
                        <dl class="layui-nav-child">
                        <#list menu.children! as subMenu>
                            <dd>
                                <a href="javascript:;" data-url="${subMenu.url!}" data-icon="&#xe614;" data-title="${subMenu.name!}" kit-target data-id='4'>
                                    <i class="layui-icon">&#xe614;</i><span> ${subMenu.name!}</span></a>
                            </dd>
                        </#list>
                        </dl>
                    </li>
                </#list>

            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            加载首页
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2017 &copy;
        <a href="http://kit.zhengjinfan.cn/">kit.zhengjinfan.cn/</a> MIT license

    </div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: 'build/js/'
    }).use(['layer','jquery','form','table','app', 'message'], function() {
        var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;

        //主入口
        app.set({
            type: 'iframe'
        }).init();
        $('#pay').on('click', function() {
            layer.open({
                title: false,
                type: 1,
                content: '<img src="/static/build/images/pay.png" />',
                area: ['500px', '250px'],
                shadeClose: true
            });
        });

        //初始化操作
        $(function () {

        });

    });
</script>
</body>

</html>