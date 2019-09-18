<@compress single_line=true>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>wx-dev</title>
    <#include "common/common.ftl">
</head>
<body>
<#include "common/header.ftl">
<div class="content" id="mbox-content">
    <a style="display: block" href="${ctxPath}/wxtest">微信 jssdk 接口测试</a>
    <#--<div class="msg-page"><div class="img"></div><div class="text">加载中...</div></div>-->
</div>

<div class="loading" style="display: none;" id="loading">
    <div class="msg">
        <div class="img"></div>
        <div id="loading-msg" class="text">数据加载中...</div>
    </div>
</div>

<div class="loading" style="display: none;" id="dialog">
    <div class="dialog">
        <div class="dialog-content">
            <div class="title">提示</div>
            <div class="text" id="dialog-msg">消息</div>
        </div>
        <div class="dialog-btns">
            <div class="dialog-btn" id="dialog-btn">知道了</div>
        </div>
    </div>
</div>



<script>

    var wxdev = {};

    (function (wxdev) {
        wxdev.common = {
            'loadPage': loadPage,
            'hideLoading': hideLoading,
            'showLoading': showLoading,
            'showDialog': showDialog,
            'closeDialog': closeDialog
        };

        function showDialog(msg, fn) {
            $("#dialog-msg").text(msg);
            $("#dialog-btn").off('click');
            if(typeof fn === 'function'){
                $("#dialog-btn").on('click', function () {
                    fn();
                });
            }else {
                $("#dialog-btn").on('click', function () {
                    closeDialog();
                });
            }
            $("#dialog").show();
        }

        function closeDialog() {
            $("#dialog").hide();
        }

        function showLoading(msg) {
            if(typeof msg == "string"){
                $("#loading-msg").text(msg);
            }
            $("#loading").show();
        }

        function hideLoading() {
            $("#loading").hide()
        }
        
        function loadPage(url, fn) {

            var html = '<div class="msg-page"><div class="img"></div><div class="text">加载中...</div></div>';
            $('#mbox-content').html(html);

            if(typeof fn === 'function'){
                $('#mbox-content').load(url, function (data, status, xhr) {
                    fn();
                });
            }else {
                $('#mbox-content').load(url);
            }

        }

        // wxdev.common.loadPage(ctxPath + "/wxtest");

    })(wxdev);

</script>

</body>
</html>
</@compress>