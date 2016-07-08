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
    <title>创建食谱 - 杉菜食谱</title>

    <%--Bootstrap--%>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-wysiwyg.css" rel="stylesheet">

    <link href="/css/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css"
          rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <%--jQuery (necessary for Bootstrap's JavaScript plugins)--%>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.hotkeys.js"></script>
    <script src="/js/bootstrap-wysiwyg.js"></script>
    <script src="/js/google-code-prettify/prettify.js"></script>

    <%--Custom styles for this template--%>
    <link href="/css/cookbook.css" rel="stylesheet">
    <link href="/css/cookbook/wysiwyg.css" rel="stylesheet">
    <%--Custom javascripts for this template--%>
    <script src="/js/cookbook.js"></script>
    <script src="/js/cookbook/wysiwyg.js"></script>

</head>

<body>
<jsp:include page="../inc/header.jsp"></jsp:include>

<div class="container mastcontainer">
    <form role="form">
        <div class="form-group">
            <label for="cookBookTitle">标题</label>
            <input type="text" class="form-control" id="cookBookTitle" placeholder="请输入标题">
        </div>
        <div class="form-group">
            <label for="cookBookMaterials">原料</label>
            <input type="text" class="form-control" id="cookBookMaterials" placeholder="请输入原料">
        </div>
        <div class="form-group">
            <label for="cookBookDescription">摘要</label>
            <input type="" class="form-control" id="cookBookDescription" placeholder="请输入摘要">
        </div>
        <div class="form-group">
            <label for="wysiwyg">制作过程</label>
            <div id="wysiwyg" class="wysiwyg">
                <div id="alerts"></div><!--错误显示区-->
                <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                    <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                        </ul>
                    </div>
                    <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i
                                class="icon-text-height"></i>&nbsp;<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
                            <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
                            <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
                        <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
                        <a class="btn" data-edit="strikethrough" title="Strikethrough"><i
                                class="icon-strikethrough"></i></a>
                        <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
                                class="icon-underline"></i></a>
                    </div>
                    <div class="btn-group">
                        <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="icon-list-ul"></i></a>
                        <a class="btn" data-edit="insertorderedlist" title="Number list"><i
                                class="icon-list-ol"></i></a>
                        <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i
                                class="icon-indent-left"></i></a>
                        <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="icon-indent-right"></i></a>
                    </div>
                    <div class="btn-group">
                        <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i
                                class="icon-align-left"></i></a>
                        <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
                                class="icon-align-center"></i></a>
                        <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
                                class="icon-align-right"></i></a>
                        <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
                                class="icon-align-justify"></i></a>
                    </div>
                    <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i
                                class="icon-link"></i></a>
                        <div class="dropdown-menu input-append">
                            <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
                            <button class="btn" type="button">Add</button>
                        </div>
                        <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="icon-cut"></i></a>

                    </div>

                    <div class="btn-group">
                        <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i
                                class="icon-picture"></i></a>
                        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage"/>
                    </div>
                    <div class="btn-group">
                        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
                        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
                    </div>
                    <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
                </div>

                <div id="editor" contenteditable="true">
                    Go ahead&hellip;
                </div>


            </div>
        </div>

        <div class="form-group">
            <label for="cookBookTags">标签</label>
            <input type="text" class="form-control" id="cookBookTags" placeholder="请输入标签">
        </div>
        <div class="form-group">
            <label for="cookBookCategories">分类</label>
            <input type="text" class="form-control" id="cookBookCategories" placeholder="请输入分类">
        </div>
        <div class="form-group">
            <label for="cookBookDegree">难度系数</label>
            <select id="cookBookDegree" class="form-control">
                <option value="1">小菜一碟</option>
                <option value="2">游刃有余</option>
                <option value="3" selected>家常快餐</option>
                <option value="4">小试牛刀</option>
                <option value="5">九牛二虎</option>
            </select>
        </div>
        <a class="btn btn-lg btn-primary btn-block" href="javascript:;" onclick="cookbook_create()">创建</a>

    </form>
</div>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>