<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>孤独的旅行家</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/static/css/login.css" media="all" />
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
    <style type="text/css">
        .loginTip{
            height: 50px;
            width: 260px;
            padding: 20px;
            border-radius: 4px;
            position: absolute;
            left: 50%;
            top: 85%;
            margin: -150px 0 0 -150px;
            z-index: 99;
        }
        .loginTip span{
            text-align: center;
            color: #fff;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div id="bg-body"></div>
<div class="login">
    <h1>孤独的旅行家</h1>
    <form class="layui-form" action="/login/main" method="post">
        <div class="layui-form-item">
            <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="/login/genCaptcha" width="116" height="36" id="mycode"></div>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
        </div>
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
    </form>
</div>
<div class="loginTip">
    <span>用户名:admin &nbsp;&nbsp;&nbsp;密码:123456</span>
</div>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.bcat.bgswitcher.js"></script>
<script>
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $(document).ready(function() {
            /*var srcBgArray = ["https://static.mysiteforme.com/chun.jpg",
                "https://static.mysiteforme.com/xia.jpg",
                "https://static.mysiteforme.com/qiu.jpg",
                "https://static.mysiteforme.com/dong.jpg"];*/
            var srcBgArray = [
                "https://static.mysiteforme.com/xia.jpg"
                ];
            $('#bg-body').bcatBGSwitcher({
                timeout:5000,
                urls: srcBgArray,
                alt: 'Full screen background image'
            });
        });

        $("#mycode").on('click',function(){
            var t = Math.random();
            $("#mycode")[0].src="/login/genCaptcha?t= "+t;
        });

        form.on('submit(login)', function(data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            if($('form').find('input[type="checkbox"]')[0].checked){
                data.field.rememberMe = true;
            }else{
                data.field.rememberMe = false;
            }
            $.post(data.form.action, data.field, function(res) {
                layer.close(loadIndex);
                if(res.success){
                    location.href="/"+res.data.url;
                }else{
                    layer.msg(res.message);
                    $("#mycode").click();
                }
            });
            return false;
        });
    });
</script>
</body>
</html>