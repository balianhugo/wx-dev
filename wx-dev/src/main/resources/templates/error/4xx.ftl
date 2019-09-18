<!doctype html>
<#assign ctxPath = springMacroRequestContext.contextPath>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>wx-dev</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
        }
        .error-pic{
            /*position: absolute;*/
            /*top: 20%;*/
            /*left: calc(50% - 90px);*/
            padding-top: 20%;
            text-align: center;
            height: calc(100% - 76px);
        }
        .error-pic > img {
            border-radius: 50%;
        }
        .error-pic .error-btn {
            margin-top: 30px;
            padding: 10px;
            border-radius: 20px;
            background-color: #dadada;
            color: #777777;
            width: 120px;
            display: inline-block;
        }

    </style>
</head>
<body>
<div class="error-pic">
    <img src="${ctxPath}/static/image/180.jpg" alt="">
    <h3>${status}</h3>
    <#if status == 404>
        <div>页面出错了，稍后再试试吧</div>
    <#else>
       <div>页面加载失败，稍后再试试吧</div>
    </#if>

    <div class="error-btn" onclick="window.location.reload();">重新加载</div>
</div>

</body>
</html>