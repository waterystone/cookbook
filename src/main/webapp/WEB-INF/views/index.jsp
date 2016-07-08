<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <%--为了确保适当的绘制和触屏缩放--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随期后！--%>
    <link rel="icon" href="/icon/favicon.ico">
    <title>首页 - 杉菜食谱</title>

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

<body>
<jsp:include page="inc/header.jsp"></jsp:include>
<div class="container mastcontainer">
    <div class="row">
        <div class="col-xs-12 col-md-9">
            <%--Carousel--%>
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <%--Indicators--%>
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="/pic/dog.jpg" id="first-slide" alt="海底世界">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>Example headline.</h1>
                                <p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and
                                    "previous"
                                    Glyphicon buttons on the left and right might not load/display properly due to web
                                    browser
                                    security rules.</p>
                                <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="/pic/dog.jpg" id="second-slide" alt="宠物狗">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>Another example headline.</h1>
                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi
                                    porta
                                    gravida
                                    at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="/pic/dog.jpg" id="third-slide" alt="瀑布">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>One more for good measure.</h1>
                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi
                                    porta
                                    gravida
                                    at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div> <!--end of Carousel-->

            <div class="row">
                <div class="col-md-6">
                    <div class="page-header">
                        <h2 class="text-center">最新食谱</h2>
                    </div>
                    <div id="divRecentCookBook" class="cookbookList">
                        <c:forEach items="${recentCookBookList}" var="item" varStatus="status">
                            <div class="row">
                                <div class="col-md-10"><a href="cookbook/book/${item.id}">${item.title}</a></div>
                                <div class="col-md-2">${item.cnt}次</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="page-header">
                        <h2 class="text-center">最近烹饪</h2>
                    </div>
                    <div id="divRecentCookingBook" class="cookbookList">
                        <c:forEach items="${recentCookingBookList}" var="item" varStatus="status">
                            <div class="row">
                                <div class="col-md-10"><a href="cookbook/book/${item.id}">${item.title}</a></div>
                                <div class="col-md-2">${item.cnt}次</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="page-header">
                        <h2 class="text-center">烹饪榜</h2>
                    </div>
                    <div id="divCookingCountRankBook" class="cookbookList">
                        <c:forEach items="${cookingCountRankBookList}" var="item" varStatus="status">
                            <div class="row">
                                <div class="col-md-10"><a href="cookbook/book/${item.id}">${item.title}</a></div>
                                <div class="col-md-2">${item.cnt}次</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="page-header">
                        <h2 class="text-center">猜你喜欢</h2>
                    </div>
                    <div id="divGuessLikeCookBook" class="cookbookList">
                        <c:forEach items="${guessLikeCookBookList}" var="item" varStatus="status">
                            <div class="row">
                                <div class="col-md-10"><a href="cookbook/book/${item.id}">${item.title}</a></div>
                                <div class="col-md-2">${item.cnt}次</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">原料</div>
                <div>
                    <c:forEach items="${materials}" var="item" varStatus="status">
                        <a class="btn btn-primary btn-xs" role="button">${item}</a>
                    </c:forEach>
                </div>
            </div>

            <div class="page-header">
                <h3>标签</h3>
            </div>
            <div>
                <c:forEach items="${tags}" var="item" varStatus="status">
                    <a class="btn btn-primary btn-xs" role="button">${item}</a>
                </c:forEach>
            </div>

            <div class="page-header">
                <h3>分类</h3>
            </div>
            <div>
                <c:forEach items="${categories}" var="item" varStatus="status">
                    <a class="btn btn-primary btn-xs" role="button">${item}</a>
                </c:forEach>
            </div>

            <div class="page-header">
                <h3>难度</h3>
            </div>
            <div>
                <c:forEach items="${degrees}" var="item" varStatus="status">
                    <a class="btn btn-primary btn-xs" role="button">${item}</a>
                </c:forEach>
            </div>
        </div>
    </div>


</div>
<jsp:include page="inc/footer.jsp"></jsp:include>


</body>
</html>