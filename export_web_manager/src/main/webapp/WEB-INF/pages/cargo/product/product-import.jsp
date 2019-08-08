<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>上传货物</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/cargo/contract/list.do">购销合同</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <div class="callout callout-info">
            <h4>重要提示</h4>
            推荐<a>下载模板文件</a>，填写后上传
        </div>

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">导入货物</div>
            <form id="editForm" action="${ctx}/cargo/contractProduct/import.do" method="post" enctype="multipart/form-data">
                <input type="text"  name="contractId" value="${contractId}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">选择文件</div>
                    <div class="col-md-4 data">
                        <input type="file" class="form-control" placeholder="选择文件" name="file">
                    </div>
                    <p class="help-block">每次导入仅可添加100条货物。生产厂家,货号,数量,包装单位,装率,箱数,单价为必填项
                    </p>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->
</div>
<!-- 内容区域 /-->
</body>

</html>