<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${basePath}/static/plugins/layui/${language}/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${basePath}/static/css/main.css" media="all" />
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
</head>
<body class="childrenBody">
<#import "spring.ftl" as spring/>
<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
<#if (mainMenu?size>0)>
    <#list mainMenu as items>
        <div class="panel col <#if (!items_has_next)>max_panel</#if> ">
            <a href="javascript:" data-url="${basePath}${items.url}">
                <div class="panel_icon" style="background-color: #54ade8">
                    <i class="layui-icon" data-icon="${items.icon}">${items.icon}</i>
                </div>
                <div class="panel_word newMessage">
                    <span>${items.dataCount}</span>
                    <cite>${items.name}</cite>
                </div>
            </a>
        </div>
    </#list>
</#if>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;font-weight: bold;">
    角色KEY命名规则==》 全小写英文字母<br>
    权限KEY命名规则==》 1.全小写英文字母&nbsp;  2.模块:业务:功能（sys:user:list，sys:user:add，sys:user:edit，sys:user:delete）<br>
    角色和权限常量均定义在下面类中：<br>
    com.cic.component.shiro.PermissionsConstant<br>
    com.cic.component.shiro.RoleConstant<br>
    shiro标签使用说明：<br>
    https://www.cnblogs.com/fancongcong/p/8093258.html
    <br>
    国际化
    1.请参考登录页<br>
    2.将自己模块所需对应的语言文本配置到language_resource表中，可视化页面正在开发中<br>
    3.前台页面加入 <\\  #import "spring.ftl" as spring/>  引入的标签放在body元素中<br>
    4.在Controller上加上模块名的注解 @i18n (非必须)<br>
    &nbsp; &nbsp;  4.1 若一个页面有多个请求方法则均需加上 @i18n<br>
    &nbsp;&nbsp;   4.2 不加注解 @i18n的情况下默认以类名前缀作为模块名 如：loginController 则模块名为：login<br>
    &nbsp;&nbsp;   4.2 模块名优先级  方法名上的注解 > Class类名上的注解 > Class类名前缀<br>
    5.开始使用 <\\ @spring.message "hello" /><br>
    6.不使用注解则国际化key = 模块名(类名前缀)_key_语言   如：login_name_en<br>
    7.后台check的错误信息国际化调用BaseController的 getValue()
    <br>
    缓存
    1.使用 redisClient.putObject("userName",username); 存储的key为：前缀(一般为poolId) + "_" + version + key<br>
    2.上例key为：  cic-backend_1.0_username
    <br>
    8.layui多语言接入
    <br>
    <-- link rel="stylesheet" href="${basePath}/static/plugins/layui/${language}/css/layui.css" media="all" />
    <-- script type="text/javascript" src="${basePath}/static/plugins/layui/${language}/layui.js"></--script>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
    <button class="layui-btn" id="pop">弹出框</button>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
    <button class="layui-btn" id="win">新窗口</button>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
    <button class="layui-btn" id="up">上传</button>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
    <button class="layui-btn" id="bb">包包</button>
</div>

<div class="panel_box row" style="margin-top: 15px;margin-left: 0px;">
    <button class="layui-btn" id="category">类目测试</button>
</div>
<script type="text/javascript" src="${basePath}/static/plugins/layui/${language}/layui.js"></script>
<script>
    var baseUrl = "${basePath}";
    layui.config({
        base: baseUrl+'/static/js/' //假设这是你存放拓展模块的根目录
    });
    layui.use(['layer','jquery','form','cicCommon'],function(){
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                cicCommon = layui.cicCommon;

        $(".panel a").on("click",function(){
            window.parent.addTab($(this));
        });
        $(document).on('click','#pop',function(){
            cicCommon.open("title","600px","450px","/sysUser/changePassword",function () {
                cicCommon.errorMsg("this is pop.");
            });
        });

        $(document).on('click','#win',function(){
          cicCommon.addTab("/sysUser/changePassword","修改密码111","&#xe613;");
        });

        $(document).on('click','#bb',function(){
            cicCommon.addTab("/cicCategory/page","翻译api","&#xe613;");
        });

        $(document).on('click','#up',function(){
            cicCommon.addTab("/upload/test","图片上传","&#xe62f;");
        });
        $(document).on('click','#category',function(){
            cicCommon.addTab("/cicCategory/ztreeTest","类目管理","&#xe62f;");
        });
    });
</script>
</body>
</html>