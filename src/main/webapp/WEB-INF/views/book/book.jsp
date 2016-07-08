<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <%--为了确保适当的绘制和触屏缩放--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随期后！--%>
    <link rel="icon" href="/icon/favicon.ico">
    <title>${cookBook.title} - 杉菜食谱</title>

    <%--Bootstrap--%>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <%--jQuery (necessary for Bootstrap's JavaScript plugins)--%>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <%--Custom styles for this template--%>
    <link href="/css/cookbook.css" rel="stylesheet">
    <%--Custom javascripts for this template--%>
    <script src="/js/cookbook.js"></script>

</head>

<body >
<jsp:include page="../inc/header.jsp"></jsp:include>

<div class="container mastcontainer">
    <div class="page-header">
        <h1>${cookBook.title}</h1>
        <p>创建时间:${cookBook.createTime}</p>
        <p class="lead">原料:${cookBook.materials}</p>
    </div>
    <div>
        ${cookBook.htmlContent}
    </div>
</div>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>