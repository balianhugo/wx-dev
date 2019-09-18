<!doctype html>
<html xmlns lang="zh">
<#assign ctxPath = springMacroRequestContext.contextPath>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>接口测试</title>
    <style>
        * {
            margin: 0 0;
            padding: 0 0;
            list-style: none;
            outline: none;
        }

        .content {
            padding: 10px 10px;
        }

        .btn {
            padding: 10px 10px;
            text-align: center;
            color: #ffffff;
            background-color: #51C332;
            border-radius: 5px;
            margin-top: 2rem;
        }
    </style>
</head>


<body>



<div class="content">
    <div class="btn" onclick="scanQRCode()">测试扫码</div>
</div>

<#include "common/wx.ftl">

<script>
    function scanQRCode() {
        wx.scanQRCode({
            needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                alert(result);
            }
        });
    }
</script>

</body>
</html>