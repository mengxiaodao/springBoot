/**
 * @Description: 公共组件
 * @author: chengang
 * @version 1.0.0
 */
layui.define(['layer','form','table','jquery'], function (exports) {

    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    var servletUrl = "backend.cic.com";

    var cicCommon = {

        errorMsg: function (text) {
            top.layer.msg(text, {icon: 2, time: 5000});
        },

        successMsg: function (text) {
            top.layer.msg(text, {icon: 1, time: 5000});
        },

        warnMsg: function (text) {
            top.layer.msg(text, {icon: 0});
        },

        confirm: function (title, text, callBackFunc) {
            top.layer.confirm(text, {
                title: title,
                resize: false,
                btn: [commonMessage['confirms'], commonMessage['cancel']],
                btnAlign: 'c',
                anim: 1,
                icon: 3
            }, callBackFunc, function () {

            })
        },
        isEmpty: function (value) {
            if (value == null || value == "" || typeof(value) == 'undefined') {
                return true;
            }
        },
        isNotEmpty: function (value) {
            if (value == null || value == "" || typeof(value) == 'undefined') {
                return false;
            } else {
                return true;
            }
        },
        invoke: function (url, param, callBackFunc, async, method) {
            // if(cicCommon.isNotEmpty(servletUrl)){
            //     url = servletUrl + url;
            // }
            if (cicCommon.isEmpty(async)) {
                async = true;
            }
            if (cicCommon.isEmpty(method)) {
                method = "post";
            }
            //打开加载层
            var index = layer.load();
            $.ajax({
                url: url,
                type: method,
                async: async,
                data: param,
                dataType: "json",
                success: function (result) {
                    callBackFunc(result);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    var status = XMLHttpRequest.status;
                    if (status == 404) {
                        cicCommon.errorMsg("请求地址出错!");
                    } else if (status == 302) {
                        cicCommon.errorMsg('连接网页出错!');
                    } else if (textStatus == "timeout") {
                        cicCommon.errorMsg("请求超时!");
                    } else {
                        cicCommon.errorMsg('请求异常!');
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    //关闭加载层
                    layer.close(index);
                }
            });
        },
        //弹出新页面窗口
        open: function (_title, _width, _height, _url, _end, isMaximize) {
            if (cicCommon.isEmpty(_width)) {
                _width = "700px";
            }
            if (cicCommon.isEmpty(_height)) {
                _height = "400px";
            }
            if (parseInt(_width.replace(/[^0-9]/ig, "")) > $(window.top.document).width()) {
                _width = $(window.top.document).width() + "px";
            }
            if (parseInt(_height.replace(/[^0-9]/ig, "")) > $(window.top.document).height()) {
                _height = $(window.top.document).height() + "px";
            }

            var index = top.layer.open({
                type: 2,
                title: _title,
                area: [_width, _height],
                fixed: true, //不固定
                scrollbar: true,
                maxmin: true,
                content: _url,
                end: _end
            });
            if (isMaximize == "1") {
                top.layer.full(index);
            }
        },
        /**
         * 打开新窗口
         * @param thisObj
         */
        addTab:function (url,name,icon) {
            var a = ' <a href="javascript:" data-url="'+url+'">';
            a += '<i class="layui-icon" data-icon="'+icon+'">'+icon+'</i>';
            a += '  <cite>'+name+'</cite>';
            a += '</a>';
            window.parent.addTab($(a));
        },
        closeTab:function () {
            // var curmenu = window.sessionStorage.getItem("curmenu")=="undefined" ? undefined : window.sessionStorage.getItem("curmenu")=="" ? '' : JSON.parse(window.sessionStorage.getItem("curmenu"));
            // var layId = curmenu.layId;
            // var title = curmenu.title;
            //
            // var close = $('li[lay-id="'+layId+'"]');
            // //console.info(close);
             window.parent.closeTab();
            // top.$("#top_tabs li.layui-this .layui-tab-close").click();
        },
        //imgId img的id
        //inputId 隐藏input框的id
        choosePic:function(imgId,inputId,callBackFun){
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: picCenterMessage['picCenter_layer_title'],
                 /*   title: "图片选择",*/
                    maxmin: true,
                    area: ['860px', '600px'],
                    content: '../upload/singlePage?isIfream=1&imgId='+imgId+'&inputId='+inputId,
                    end: function(){
                        if(typeof callBackFun == 'function'){
                            callBackFun();
                        }
                    }
                });
            });
        },

        //clazzId 多个img的外层div class id  必传
        //least 最少上传  非必传
        //limit 最大上传几个图片  最多8个  必传
        chooseMultiplePic:function(clazzId,least,limit,callBackFun){
            if(least <= 0){
                layer.tips(picCenterMessage['picCenter_layer_least']);
            }
            if(limit > 5){
                layer.tips(picCenterMessage['picCenter_layer_limit']);
            }
            layer.ready(function(){
                layer.open({
                    type: 2,
                    title: picCenterMessage['picCenter_layer_title'],
                    maxmin: true,
                    area: ['860px', '700px'],
                    content: '../upload/multiplePage?isIfream=1&clazzId='+clazzId+'&limit='+limit+'&least='+least,
                    end: function(){
                        if(typeof callBackFun == 'function'){
                            callBackFun();
                        }
                    }
                });
            });
        },
        /**
         * 导出弹框
         * @param obj
         */
        exportExcel:function(obj){
            $.post(obj.countUrl,obj.param,function(rs){
                if(rs.success){
                    var  count = rs.attribute.count;
                    var inm = 0;
                    var pageSize = parseInt(obj.size);
                    if(count != 0){
                        inm = count / pageSize;
                    }else{
                        this.warnMsg("抱歉，当前没有数据可供导出!")
                        return;
                    }
                    if (inm <= 1) {
                        window.location.href = obj.exportUrl + "&page=1";
                        return;
                    } else {
                        var index = parseInt(inm);
                        if (inm > index) {
                            index += 1;
                        }
                        var temp = '';
                        for (var i = 0; i < index; i++) {
                            temp += '<span style="font-size:15px;text-align:center;">' + (i * pageSize + 1) + '-' + ((i + 1) * pageSize) + '条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=' + obj.exportUrl + "&page=" + (i + 1) + '>导出</a></span><br/><hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />';
                        }
                        $('#'+obj.divId).text("").append(temp);

                        var _width = "550px",_height = "400px";
                        if (parseInt(_width.replace(/[^0-9]/ig, "")) > $(window.top.document).width()) {
                            _width = $(window.top.document).width() + "px";
                        }
                        if (parseInt(_height.replace(/[^0-9]/ig, "")) > $(window.top.document).height()) {
                            _height = $(window.top.document).height() + "px";
                        }
                        top.layer.open({
                            type: 1,
                            title: "请选择要导出的数据分组",
                            area: [_width, _height],
                            scrollbar: true,
                            fixed: true, //不固定
                            shadeClose: true,   //点击遮罩关闭
                            content:   $('#'+obj.divId).html()
                        });
                       // top.layer.full(index);
                    }
                }
            });
        },
        //写cookies
        setCookie: function (name, value) {
            var Days = 7;
            var exp = new Date();
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
            document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() +";path=/;";
        },
        //读取cookies
        getCookie: function (name) {
            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        },
        //删除cookies
        delCookie: function (name) {
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval = this.getCookie(name);
            if (cval != null)
                document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
        }
};
    exports('cicCommon', cicCommon);
});
