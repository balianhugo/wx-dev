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
            background-color: #ffffff;
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
        .error-pic > div {
           padding: .5rem 3rem;
        }

    </style>
</head>
<body>
<div class="error-pic">
    <img src="${ctxPath}/static/image/180.jpg" alt="">
    <h3>公众号未关注</h3>
   <div>您还没有关注“xxx”公众号，请关注后再进行相关操作！</div>
</div>
</body>
</html>