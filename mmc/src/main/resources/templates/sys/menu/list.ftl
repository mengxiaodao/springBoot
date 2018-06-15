<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>菜单权限管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/static/build/css/app.css" media="all">
    <style>
        .layui-table .value_col{
            text-align: center;
        }
        .layui-btn-mt{
            margin-top: 5px;
        }
    </style>
</head>

<body class="childrenBody">

<fieldset class="layui-elem-field">
    <legend>系统菜单</legend>
    <div class="layui-field-box">
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal">添加根菜单</a>
        </div>
    </div>
</fieldset>

<div class="layui-form users_list">
    <div id="menuTree"></div>
</div>

</body>
<script type="text/javascript" src="/static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>

<script type="text/javascript">
    layui.config({
        base: 'build/js/'
    });
    layui.use(['layer','jquery','form','table','app', 'message'], function() {
        var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;

        var layout = [
            {
                name: 'ID',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 3%',
                render:function(row){
                    return undefined === row.id?"" : row.id;
                }
            },
            { name: '菜单名称', treeNodes: true, headerClass: 'value_col'},
            { name: '菜单英文名称', headerClass: 'value_col',
                render:function(row){
                    return undefined === row.enName?"" : row.enName;
                }
            },
            {
                name: '菜单唯一标识',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 15%',
                render:function(row){
                    return undefined === row.menuKey?"" : row.menuKey;
                }
            },
            {
                name: '菜单权限',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 15%',
                render:function(row){
                    return undefined === row.permission?"" : row.permission;
                }
            },
            {
                name: '菜单URL',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 15%',
                render:function(row){
                    return undefined === row.url?"" : row.url;
                }
            },
            {
                name: '图标',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%',
                render:function(row){
                    return undefined === row.icon?"" : '<i class="layui-icon" style="font-size: 30px;">'+row.icon+'</i>';
                }
            },
            {
                name: '排序',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%',
                render:function(row){
                    return undefined === row.sort?"" : row.sort;
                }
            },
            {
                name: '是否可见',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%',
                render:function(row){
                    return row.isVisible == 1?"是" : "否";
                }
            },
            {
                name: '',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%',
                render:function(row){
                    return row.status == 1?"是" : "否";
                }
            },
            {
                name: '操作',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 15%',
                render: function(row) {
                    var str = '';
                    if (row.isLeaf == 0 ) {
                        str += '<a class="layui-btn layui-btn-normal layui-btn-sm layui-btn-mt addMenu" data-value="' + row.id + '"><i class="layui-icon">&#xe654;</i> 添加子菜单</a>';
                    }
                    str  += '<a class="layui-btn layui-btn-normal layui-btn-sm layui-btn-mt editMenu" data-value="' + row.id + '"><i class="layui-icon">&#xe642;</i> 编辑</a>' ;
                    str  += '<a class="layui-btn layui-btn-danger layui-btn-sm layui-btn-mt delMenu" data-value="' + row.id + '"><i class="layui-icon">&#xe640;</i>删除</a>';
                    if (row.status == 1) {
                        str += '<a class="layui-btn layui-btn-danger layui-btn-sm layui-btn-mt statusMenu" data-value="' + row.id + '" data-type="0"><i class="layui-icon">&#xe651;</i> 禁用</a>';
                    } else {
                        str += '<a class="layui-btn layui-btn-normal layui-btn-sm layui-btn-mt statusMenu" data-value="' + row.id + '" data-type="1"><i class="layui-icon">&#xe652;</i> 启用</a>';
                    }
                    return str;
                }
            }
        ];
        var setTree = function(data,layout){
            $("#menuTree").empty();
            layui.treeGird({
                elem: '#menuTree', //传入元素选择器
                nodes: data,
                layout: layout
            });
        };

        $(function(){
            $.post("/sysMenu/treeList",function(res){
                if(res.success){
                    setTree(res.attribute.menuList,layout);
                    addEvent();
                }else{
                    layer.msg(res.message);
                }
            });
        });

        $('.layui-inline .layui-btn').on('click', function(){
            cicCommon.addTab("/system/menu/add","添加系统菜单","&#xe63c;");
        });

        // 添加按钮事件
        var addEvent = function() {
            $('.addMenu').each(function(){$(this).on('click', function(){
                var id = $(this).data('value');
                cicCommon.addTab("${basePath}/system/menu/add?parentId="+id,"<@spring.message 'AddSystemMenu'/>","&#xe63c;");
            })});

            $('.editMenu').each(function(){$(this).on('click', function(){
                var id = $(this).data('value');
                cicCommon.addTab("${basePath}/system/menu/edit?id="+id,"<@spring.message 'EditMenu'/>","&#xe63c;");
            })});

            $('.delMenu').each(function(){$(this).on('click', function(){
                var id = $(this).data('value');
                cicCommon.confirm("<@spring.message 'Prompt'/>","<@spring.message 'AreYouDeleteMenu'/>？",function(){
                    $.post("${basePath}/system/menu/delete",{"id":id},function (res){
                        if(res.success){
                            cicCommon.successMsg("<@spring.message 'DeleteSuccess'/>!");
                            location.reload();
                        }else{
                            cicCommon.errorMsg(res.message);
                        }
                    });
                });
            })});

            $('.statusMenu').each(function(){$(this).on('click', function(){
                var id = $(this).data('value');
                var status = $(this).data('type');
                var t = (status == 1 ? "<@spring.message 'Enable'/>" : "<@spring.message 'Disable'/>");
                cicCommon.confirm("<@spring.message 'MenuPrompt'/>","<@spring.message 'SureYouWant'/>"+t+"<@spring.message 'ThisMenu'/>？",function(){
                    $.post("${basePath}/system/menu/setStatus",{"id":id,"status":status},function (res){
                        if(res.success){
                            cicCommon.successMsg(t+"<@spring.message 'Successfull'/>!");
                            location.reload();
                        }else{
                            cicCommon.errorMsg(res.message);
                        }
                    });
                });
            })});
        }

    });

</script>

</html>